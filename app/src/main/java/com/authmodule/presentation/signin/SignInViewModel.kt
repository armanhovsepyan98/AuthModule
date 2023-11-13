package com.authmodule.presentation.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.authmodule.common.Validator
import com.authmodule.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepo: AuthRepository
) : ViewModel() {

    var uiState by mutableStateOf<State>(State.Initial)
        private set

    var signInUIState = mutableStateOf(SignInUIState())

    val isSignInBtnEnabled
        get() = Validator.validateEmail(signInUIState.value.email).status
                && Validator.validatePassword(signInUIState.value.password).status


    fun onEvent(event: SignInUIEvent) {
        when (event) {
            is SignInUIEvent.EmailChanged -> {
                signInUIState.value = signInUIState.value.copy(
                    email = event.email
                )
            }

            is SignInUIEvent.PasswordChanged -> {
                signInUIState.value = signInUIState.value.copy(
                    password = event.password
                )
            }

            is SignInUIEvent.SignInBtnClicked -> {
                signInBtnClicked()
            }

            is SignInUIEvent.FacebookBtnClicked -> TODO()
            is SignInUIEvent.ForgotPasswordTxtClicked -> TODO()
            is SignInUIEvent.GoogleBtnClicked -> TODO()
            is SignInUIEvent.SignUpBtnClicked -> TODO()
        }
    }

    private fun signInBtnClicked() {
        uiState = State.Loading
        viewModelScope.launch {
            try {
                val response = authRepo.login(
                    email = signInUIState.value.email,
                    password = signInUIState.value.password
                )
                uiState = if (response) {
                    State.Success
                } else {
                    State.Error("Error")
                }
            } catch (e: Exception) {
                uiState = State.Error("Could not login")
            } finally {
                uiState = State.Initial
            }
        }
    }

}

data class SignInUIState(
    var email: String = "",
    var password: String = "",
)

sealed class SignInUIEvent {
    data class EmailChanged(val email: String) : SignInUIEvent()
    data class PasswordChanged(val password: String) : SignInUIEvent()

    object ForgotPasswordTxtClicked : SignInUIEvent()

    object SignInBtnClicked : SignInUIEvent()
    object SignUpBtnClicked : SignInUIEvent()
    object GoogleBtnClicked : SignInUIEvent()
    object FacebookBtnClicked : SignInUIEvent()
}

sealed class State {
    object Initial : State()
    object Success : State()
    object Loading : State()
    open class Error(val messageRes: String) : State()
    open class ErrorWithCode(val message: String) : State()
}