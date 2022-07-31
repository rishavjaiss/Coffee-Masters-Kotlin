package com.android.coffeemasters

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.android.coffeemasters.pages.InfoPage
import com.android.coffeemasters.pages.MenuPage
import com.android.coffeemasters.pages.OrderPage

@Composable
fun App(dataManager: DataManager) {
    var selectedRoute = remember {
        mutableStateOf("menu")
    }
    Scaffold(
        topBar = {
            TopAppBar() {
                AppTitle()
            }
        },
        bottomBar = {
            NavBar(
                selectedRoute = selectedRoute.value,
                onChange = { newRoute ->
                selectedRoute.value = newRoute
            })
        }
    ) {
        when(selectedRoute.value){
            Routes.MenuPage.route -> MenuPage(dataManager)
            Routes.OffersPage.route -> OffersPage()
            Routes.OrderPage.route -> OrderPage(dataManager)
            Routes.InfoPage.route -> InfoPage()
        }
    }
}

@Composable
fun AppTitle(){
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription= "Coffee Masters Logo")
    }
}