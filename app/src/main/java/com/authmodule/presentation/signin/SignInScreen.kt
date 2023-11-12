package com.authmodule.presentation.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.authmodule.R
import com.authmodule.presentation.components.AppButton
import com.authmodule.presentation.components.Background
import com.authmodule.presentation.components.EmailTextField
import com.authmodule.presentation.components.PasswordTextField
import com.authmodule.presentation.components.SeperatorWithText
import com.authmodule.presentation.components.SocialMediaButton

@Composable
fun SignInScreen(viewModel: SignInViewModel, onSignUpBtnClick: () -> Unit) {
    Background {
        HeaderComponent()
        InputFieldsComponent(viewModel)
        ButtonsField {
            onSignUpBtnClick()
        }
    }
}

@Composable
fun HeaderComponent() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 60.dp),
        fontSize = 50.sp,
        fontWeight = FontWeight.Bold,
        text = "SignIn",
        color = MaterialTheme.colorScheme.onPrimary,
        textAlign = TextAlign.Center
    )
}

@Composable
fun InputFieldsComponent(viewModel: SignInViewModel) {
    Spacer(modifier = Modifier.height(40.dp))
    EmailTextField(
        labelValue = "Email",
        hint = "example@gmail.com",
        textValue = viewModel.signInUIState.value.email,
        onTextChanged = {
            viewModel.onEvent(SignInUIEvent.EmailChanged(it))
        }
    )


    Spacer(modifier = Modifier.height(20.dp))

    PasswordTextField(
        labelValue = "Password",
        hint = "Enter your password",
        textValue = viewModel.signInUIState.value.password,
        onTextChanged = {
            viewModel.onEvent(SignInUIEvent.PasswordChanged(it))
        },
    )

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        fontSize = 15.sp,
        color = Color.DarkGray,
        textAlign = TextAlign.End,
        text = "Forgot Password?"
    )
}

@Composable
fun ButtonsField(onSignUpBtnClick: () -> Unit) {
    Spacer(modifier = Modifier.height(30.dp))
    AppButton(
        buttonTxt = "SignIn"
    ) {}
    Spacer(modifier = Modifier.height(30.dp))
    SeperatorWithText("Or Log In With")
    Spacer(modifier = Modifier.height(30.dp))
    SocialMediaButtonsRow()
    Spacer(modifier = Modifier.height(30.dp))
    DoesNotHaveAccountTxt()
    Spacer(modifier = Modifier.height(10.dp))
    AppButton(
        buttonTxt = "Sign Up",
        backgroundColor = Color.White
    ) {
        onSignUpBtnClick()
    }
}

@Composable
fun DoesNotHaveAccountTxt() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        fontSize = 15.sp,
        color = Color.DarkGray,
        textAlign = TextAlign.Center,
        text = "Doesn't have an account yet?",
    )
}

@Composable
fun SocialMediaButtonsRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SocialMediaButton(
            onClick = {
                // Handle Facebook button click
            },
            painter = painterResource(id = R.drawable.facebook),
            buttonText = "Facebook",
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        )
        SocialMediaButton(
            onClick = {
                // Handle Google button click
            },
            painter = painterResource(id = R.drawable.google),
            buttonText = "Google",
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        )
    }
}

