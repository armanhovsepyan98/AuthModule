package com.authmodule.presentation.signup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.authmodule.presentation.components.AppButton
import com.authmodule.presentation.components.Background
import com.authmodule.presentation.components.EmailTextField
import com.authmodule.presentation.components.FullScreenLoading
import com.authmodule.presentation.components.PasswordTextField
import com.authmodule.presentation.components.SeperatorWithText
import com.authmodule.presentation.signin.SocialMediaButtonsRow

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel,
    onLoginTxtClick: () -> Unit,
    onSignUpBtnClick: () -> Unit
) {
    Background {
        HeaderComponent()
        InputFieldsComponent(viewModel)
        ButtonsField(
            isSignUpBtnEnabled = viewModel.isSignUpBtnEnabled,
            onLoginTxtClick = {
                onLoginTxtClick()
            },
            onSignUpBtnClick = {
                onSignUpBtnClick()
            }
        )
    }

    FullScreenLoading(isLoading = viewModel.uiState is State.Loading)
}

@Composable
fun HeaderComponent() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 60.dp),
        fontSize = 50.sp,
        fontWeight = FontWeight.Bold,
        text = "SignUp",
        color = MaterialTheme.colorScheme.onPrimary,
        textAlign = TextAlign.Center
    )
}

@Composable
fun InputFieldsComponent(viewModel: SignUpViewModel) {
    Spacer(modifier = Modifier.height(40.dp))
    EmailTextField(
        labelValue = "Email",
        hint = "example@gmail.com",
        textValue = viewModel.signUpUIState.value.email,
        onTextChanged = {
            viewModel.onEvent(SignUpUIEvent.EmailChanged(it))
        }
    )

    Spacer(modifier = Modifier.height(20.dp))

    PasswordTextField(
        labelValue = "Password",
        hint = "Enter your password",
        textValue = viewModel.signUpUIState.value.password,
        onTextChanged = {
            viewModel.onEvent(SignUpUIEvent.PasswordChanged(it))
        },
    )

    Spacer(modifier = Modifier.height(20.dp))

    PasswordTextField(
        labelValue = "Confirm Password",
        hint = "Enter your password",
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        textValue = viewModel.signUpUIState.value.confirmPassword,
        onTextChanged = {
            viewModel.onEvent(SignUpUIEvent.ConfirmPasswordChanged(it))
        },
    )

    TermsAndConditionsCheckbox(
        isChecked = viewModel.signUpUIState.value.isChecked,
        onCheckedChange = {
            viewModel.onEvent(SignUpUIEvent.TermsAndConditionsChanged(it))
        }
    )
}


@Composable
fun ButtonsField(
    isSignUpBtnEnabled: Boolean,
    onLoginTxtClick: () -> Unit,
    onSignUpBtnClick: () -> Unit
) {
    Spacer(modifier = Modifier.height(30.dp))
    AppButton(
        buttonTxt = "SignUp",
        isButtonEnabled = isSignUpBtnEnabled
    ) {
        onSignUpBtnClick()
    }
    Spacer(modifier = Modifier.height(30.dp))
    SeperatorWithText("Or Sign Up with")
    Spacer(modifier = Modifier.height(30.dp))
    SocialMediaButtonsRow()
    Spacer(modifier = Modifier.height(30.dp))
    AlreadyHaveAnAccountTxt {
        onLoginTxtClick()
    }
}

@Composable
fun TermsAndConditionsCheckbox(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Checkbox(
            checked = isChecked,
            colors = CheckboxDefaults.colors(checkmarkColor = Color.White),
            onCheckedChange = {
                onCheckedChange(it)
            }
        )

        Text(
            text = "I agree to the Terms and Conditions",
            color = Color.DarkGray,
            textAlign = TextAlign.Start,
            fontSize = 15.sp
        )
    }
}


@Composable
fun AlreadyHaveAnAccountTxt(onLoginTxtClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Already have an account? ",
            color = Color.DarkGray,
            fontSize = 15.sp
        )

        Text(
            text = "Login",
            modifier = Modifier
                .clickable { onLoginTxtClick() }
                .padding(start = 4.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
    }
}

