package com.authmodule.presentation.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {

    var uiState by mutableStateOf<State>(State.Initial)
        private set

    var signInUIState = mutableStateOf(SignInUIState())

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

            is SignInUIEvent.FacebookBtnClicked -> TODO()
            is SignInUIEvent.ForgotPasswordTxtClicked -> TODO()
            is SignInUIEvent.GoogleBtnClicked -> TODO()
            is SignInUIEvent.SignInBtnClicked -> TODO()
            is SignInUIEvent.SignUpBtnClicked -> TODO()
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
    object Loading : State()
    open class Error(val messageRes: String) : State()
    open class ErrorWithCode(val message: String) : State()
}