package com.zaritcare.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import com.zaritcare.data.SettingsRepository
import com.zaritcare.ui.features.login.LoginScreen
import com.zaritcare.ui.features.splashscreen.SplashScreen
import com.zaritcare.ui.navigation.ZaritcareNavHost
import com.zaritcare.ui.theme.ZaritcareTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var settingsRepository: SettingsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch(Dispatchers.Main) {
            settingsRepository.settingsFlow.collect { settings ->
                val theme: String = settings.theme

                setContent {
                    ZaritcareTheme(
                        darkTheme = if(theme.contains("Sistema")) isSystemInDarkTheme() else theme.contains("Oscuro")
                    ) {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            ZaritcareNavHost()
                        }
                    }
                }
            }
        }
    }
}