package com.zaritcare.ui.features.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun ZaritcareNavBar(
    selectedPage: Int,
    onNavigateToForms: () -> Unit,
    onNavigateToActivities: () -> Unit,
    onNavigateToTips: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    @Immutable
    data class ItemIconButtonWithText(
        val icon: ImageVector,
        val description: String? = null,
        val title: String,
        val onClick: () -> Unit
    )

    var selectedItem: Int by remember { mutableIntStateOf(selectedPage) }
    val listItemsIconButtonsWithText:List<ItemIconButtonWithText> = listOf<ItemIconButtonWithText>(
        ItemIconButtonWithText(
            icon = Icons.Filled.ContentPaste,
            description = "reports",
            title = "Cuestionario",
            onClick = onNavigateToForms
        ),
        ItemIconButtonWithText(
            icon = Icons.Filled.SportsEsports,
            description = "activities",
            title = "Actividades",
            onClick = onNavigateToActivities
        ),
        ItemIconButtonWithText(
            icon = Icons.Filled.Star,
            description = "tips",
            title = "Consejos",
            onClick = onNavigateToTips
        ),
        ItemIconButtonWithText(
            icon = Icons.Filled.Settings,
            description = "settings",
            title = "Ajustes",
            onClick = onNavigateToSettings
        )
    )

    NavigationBar {
        listItemsIconButtonsWithText.forEachIndexed { index, button ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = button.icon,
                        contentDescription = button.description
                    )
                },
                label = {
                    Text(
                        text = button.title
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary
                ),
                selected = selectedItem == index,
                onClick = {
                    button.onClick()
                }
            )
        }
    }
}