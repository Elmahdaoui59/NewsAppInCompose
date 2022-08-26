package com.example.newsappincompose.navigation.screens.common

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.newsappincompose.navigation.screens.NavGraphs.root
import com.example.newsappincompose.navigation.screens.appCurrentDestinationAsState
import com.example.newsappincompose.navigation.screens.startAppDestination
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.DestinationSpec

@Composable
fun BottomBar(
    navController: NavController
) {
    val currentDestination: DestinationSpec<*> = navController.appCurrentDestinationAsState().value
        ?: root.startAppDestination

    BottomNavigation {
        BottomBarDestination.values().forEach { destination ->
            BottomNavigationItem(
                selected = currentDestination == destination.direction,
                onClick = {
                    navController.navigate(
                        destination.direction
                    )

                },
                icon = {
                    Icon(
                        destination.icon,
                        contentDescription = (destination.label),
                        tint = Color(destination.iconColor)
                    )
                },
                label = { Text(destination.label)},
            )
        }
    }
    
}