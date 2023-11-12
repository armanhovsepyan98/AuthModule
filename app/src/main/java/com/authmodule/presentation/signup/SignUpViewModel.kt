package com.authmodule.presentation.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

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

            is SignUpUIEvent.FacebookBtnClicked -> TODO()
            is SignUpUIEvent.GoogleBtnClicked -> TODO()
            is SignUpUIEvent.LoginHereTxtClicked -> TODO()
            is SignUpUIEvent.SignUpBtnClicked -> TODO()
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
    object Loading : State()
    open class Error(val messageRes: String) : State()
    open class ErrorWithCode(val message: String) : State()
}