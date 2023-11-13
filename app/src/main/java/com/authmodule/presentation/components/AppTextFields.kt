package com.authmodule.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(
    labelValue: String,
    hint: String,
    textValue: String,
    onTextChanged: (String) -> Unit,
    isError: Boolean = false
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = textValue,
        singleLine = true,
        onValueChange = { newValue ->
            onTextChanged(newValue)
        },
        label = {
            Text(text = labelValue)
        },
        placeholder = {
            Text(
                text = hint,
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f),
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colorScheme.primary,
        ),
        shape = RoundedCornerShape(8.dp),
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    labelValue: String,
    hint: String,
    textValue: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
    onTextChanged: (String) -> Unit,
    isError: Boolean = false
) {

    val passwordVisibility = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = textValue,
        singleLine = true,
        onValueChange = { newValue ->
            onTextChanged(newValue)
        },
        label = {
            Text(text = labelValue)
        },
        placeholder = {
            Text(
                text = hint,
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f),
            )
        },
        keyboardOptions = keyboardOptions,
        trailingIcon = {
            val iconImage = if (passwordVisibility.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            val descriptions = if (passwordVisibility.value) {
                "Hide password"
            } else {
                "Show Password"
            }

            IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                Icon(
                    imageVector = iconImage,
                    contentDescription = descriptions,
                    tint = MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)
                )
            }
        },
        visualTransformation = if (passwordVisibility.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colorScheme.primary,
        ),
        shape = RoundedCornerShape(8.dp),
    )
}