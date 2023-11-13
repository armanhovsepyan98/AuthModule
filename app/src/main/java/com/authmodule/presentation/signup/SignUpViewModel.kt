package com.authmodule.presentation.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.authmodule.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepo: AuthRepository
) : ViewModel() {

    var uiState by mutableStateOf<State>(State.Initial)
        private set

    var signUpUIState = mutableStateOf(SignUpUIState())

    fun onEvent(event: SignUpUIEvent) {
        when (event) {
            is SignUpUIEvent.EmailChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    email = event.email
                )
            }

            is SignUpUIEvent.PasswordChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    password = event.password
                )
            }

            is SignUpUIEvent.ConfirmPasswordChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    confirmPassword = event.confirmPassword
                )
            }

            is SignUpUIEvent.TermsAndConditionsChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    isChecked = event.isChecked
                )
            }

            SignUpUIEvent.SignUpBtnClicked -> {
                signUpBtnClicked()
            }

            SignUpUIEvent.FacebookBtnClicked -> TODO()
            SignUpUIEvent.GoogleBtnClicked -> TODO()
            SignUpUIEvent.LoginHereTxtClicked -> TODO()
        }
    }

    private fun signUpBtnClicked() {
        uiState = State.Loading
        viewModelScope.launch {
            try {
                val response = authRepo.register(
                    email = signUpUIState.value.email,
                    password = signUpUIState.value.password
                )
                uiState = if (response) {
                    State.Success
                } else {
                    State.Error("Error")
                }
            } catch (e: Exception) {
                uiState = State.Error("Could not register")
            } finally {
                uiState = State.Initial
            }
        }
    }
}

data class SignUpUIState(
    var email: String = "",
    var password: String = "",
    var confirmPassword: String = "",
    var isChecked: Boolean = false
)

sealed class SignUpUIEvent {
    data class EmailChanged(val email: String) : SignUpUIEvent()
    data class PasswordChanged(val password: String) : SignUpUIEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : SignUpUIEvent()
    data class TermsAndConditionsChanged(val isChecked: Boolean) : SignUpUIEvent()

    object SignUpBtnClicked : SignUpUIEvent()
    object GoogleBtnClicked : SignUpUIEvent()
    object FacebookBtnClicked : SignUpUIEvent()
    object LoginHereTxtClicked : SignUpUIEvent()
}

sealed class State {
    object Initial : State()
    object Success : State()
    object Loading : State()
    open class Error(val messageRes: String) : State()
    open class ErrorWithCode(val message: String) : State()
}