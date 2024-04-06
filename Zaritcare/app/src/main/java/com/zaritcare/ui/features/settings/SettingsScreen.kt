package com.zaritcare.ui.features.settings

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contrast
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kinoyamboladmin.ui.features.settings.SettingsUiState
import com.zaritcare.ui.composables.OutlinedDropdownMenu
import com.zaritcare.ui.composables.TextBody
import com.zaritcare.ui.features.components.ZaritcareNavBar
import com.zaritcare.ui.features.results.Content

@Composable
fun TextProperty(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    description: String?,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.clickable { onClick() },
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            tint = MaterialTheme.colorScheme.onBackground,
            imageVector = icon,
            contentDescription = description
        )
        TextBody(text = text)
    }
}

@Composable
fun DropdownMenuProperty(
    modifier: Modifier = Modifier,
    options: List<String>,
    selectedOption: String,
    label: String,
    onChangeValue: (String) -> Unit,
    icon: ImageVector,
    description: String?
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            tint = MaterialTheme.colorScheme.onBackground,
            imageVector = icon,
            contentDescription = description
        )
        OutlinedDropdownMenu(
            options = options,
            selectedOption = selectedOption,
            label = label,
            onChangeValue = onChangeValue
        )
    }
}

@Composable
fun Properties(
    modifier: Modifier = Modifier,
    themes: List<String>,
    settingsUiState: SettingsUiState,
    onChangeTheme: (String) -> Unit,
    onClickPrivacyPolicies: () -> Unit,
    onClickTermsAndConditions: () -> Unit
) {
    val context: Context = LocalContext.current
    @Immutable
    data class DropdownMenuPropertyUiState(
        val label: String = "",
        val icon: ImageVector = Icons.Filled.Settings,
        val selectedOption: String = "",
        val description: String? = null,
        val options: List<String> = emptyList(),
        val onChangeValue: (String) -> Unit
    )
    @Immutable
    data class TextPropertyUiState(
        val icon: ImageVector = Icons.Filled.Settings,
        val text: String = "",
        val description: String? = null,
        val onClick: () -> Unit
    )

    val contentDropdownsMenu: List<DropdownMenuPropertyUiState> = listOf(
        DropdownMenuPropertyUiState(
            label = "Tema",
            description = "theme",
            options = themes,
            selectedOption = settingsUiState.theme,
            icon = Icons.Filled.Contrast,
            onChangeValue = onChangeTheme
        )
    )
    val contentText: List<TextPropertyUiState> = listOf(
        TextPropertyUiState(
            text = "Política de privacidad",
            icon = Icons.Filled.Lock,
            description = "privacy",
            onClick = onClickPrivacyPolicies
        ),
        TextPropertyUiState(
            text = "Términos y condiciones",
            icon = Icons.Filled.Info,
            description = "terms",
            onClick = onClickTermsAndConditions
        )
    )

    Column(
        modifier = modifier
    ) {
        contentDropdownsMenu.forEach { dropdownMenu ->
            DropdownMenuProperty(
                modifier = Modifier.padding(bottom = 16.dp),
                options = dropdownMenu.options,
                selectedOption = dropdownMenu.selectedOption,
                label = dropdownMenu.label,
                onChangeValue = dropdownMenu.onChangeValue,
                icon = dropdownMenu.icon,
                description = dropdownMenu.description
            )
        }
        contentText.forEach { iconWithText ->
            TextProperty(
                modifier = Modifier
                    .height(45.dp)
                    .fillMaxWidth(),
                icon = iconWithText.icon,
                description = iconWithText.description,
                text = iconWithText.text,
                onClick = iconWithText.onClick
            )
        }
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    themes: List<String>,
    settingsUiState: SettingsUiState,
    onChangeTheme: (String) -> Unit,
    onClickPrivacyPolicies: () -> Unit,
    onClickTermsAndConditions: () -> Unit
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Properties(
            themes = themes,
            settingsUiState = settingsUiState,
            onChangeTheme = onChangeTheme,
            onClickPrivacyPolicies = onClickPrivacyPolicies,
            onClickTermsAndConditions = onClickTermsAndConditions
        )
    }
}

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    themes: List<String>,
    settingsUiState: SettingsUiState,
    onSettingsEvent: (SettingsEvent) -> Unit,
    onNavigateToResults: () -> Unit,
    onNavigateToActivities: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToTips: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        content = { paddingValues ->
            MainContent(
                modifier = modifier.padding(paddingValues = paddingValues),
                themes = themes,
                settingsUiState = settingsUiState,
                onChangeTheme = { onSettingsEvent(SettingsEvent.OnChangeTheme(it)) },
                onClickPrivacyPolicies = { onSettingsEvent(SettingsEvent.OnClickPrivacyPolicies) },
                onClickTermsAndConditions = { onSettingsEvent(SettingsEvent.OnClickTermsAndConditions) }
            )
        },
        bottomBar = {
            ZaritcareNavBar(
                onNavigateToForms = onNavigateToResults,
                onNavigateToActivities = onNavigateToActivities,
                onNavigateToTips = onNavigateToTips,
                onNavigateToSettings = onNavigateToSettings,
                selectedPage = 3
            )
        }
    )
}