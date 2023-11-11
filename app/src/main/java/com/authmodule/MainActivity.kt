package com.authmodule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.authmodule.navigation.Navigation
import com.authmodule.ui.theme.AuthModuleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AuthModuleTheme {
                Navigation()
            }
        }
    }
}
