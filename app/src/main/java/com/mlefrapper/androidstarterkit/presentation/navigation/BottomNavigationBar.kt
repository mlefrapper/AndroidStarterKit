package com.mlefrapper.androidstarterkit.presentation.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mlefrapper.androidstarterkit.ui.theme.Neutral10
import com.mlefrapper.androidstarterkit.ui.theme.Neutral5
import com.mlefrapper.androidstarterkit.ui.theme.Neutral50
import com.mlefrapper.androidstarterkit.ui.theme.Primary50

@Composable
fun BottomNavigationBar(
    navController: NavController,
    items: List<BottomBarDestination>,
    modifier: Modifier = Modifier,
    onItemClick: (BottomBarDestination) -> Unit,
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar(
        modifier = modifier,
        containerColor = Neutral5,
    ) {
        items.forEach { bottomNavItem ->
            val isSelected = currentRoute == bottomNavItem.route::class.qualifiedName
            NavigationBarItem(
                selected = isSelected,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Neutral10,
                ),
                onClick = {
                    if (!isSelected) {
                        onItemClick(bottomNavItem)
                    }
                },
                label = {
                    Text(
                        text = stringResource(
                            id = bottomNavItem.labelResId,
                        ),
                        color = if (isSelected) {
                            Primary50
                        } else {
                            Neutral50
                        },
                    )
                },
                icon = {
                    Image(
                        painter = painterResource(
                            id = bottomNavItem.iconResId,
                        ),
                        contentDescription = stringResource(
                            id = bottomNavItem.labelResId,
                        ),
                        colorFilter = ColorFilter.tint(
                            color = if (isSelected) {
                                Primary50
                            } else {
                                Neutral50
                            },
                        ),
                        modifier = Modifier.size(26.dp),
                    )
                },
            )
        }
    }
}
