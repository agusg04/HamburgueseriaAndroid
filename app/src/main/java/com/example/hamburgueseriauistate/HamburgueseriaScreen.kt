package com.example.hamburgueseriauistate

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hamburgueseriauistate.ui.HamburgueseriaModelView
import com.example.hamburgueseriauistate.ui.PantallaHistorico
import com.example.hamburgueseriauistate.ui.PantallaIngrediente
import com.example.hamburgueseriauistate.ui.PantallaPedido
import com.example.hamburgueseriauistate.ui.PantallaPrincipal

enum class HamburgueseriaScreen(@StringRes val title: Int) {
    PantallaPrincipal(title = R.string.pantalla_principal),
    PantallaPedido(title = R.string.pantalla_pedido),
    PantallaHistorico(title = R.string.pantalla_historico),
    PantallaIngredientes(title = R.string.pantalla_ingredientes)

}

@Composable
fun HamburgueseriaApp(navController: NavHostController = rememberNavController()){

    val viewModelHamburgeseria: HamburgueseriaModelView = viewModel()

    val uiState by viewModelHamburgeseria.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = HamburgueseriaScreen.PantallaPrincipal.name
    ){
        composable(route = HamburgueseriaScreen.PantallaPrincipal.name) {
            PantallaPrincipal(
                onClickVerPedido = { navController.navigate(HamburgueseriaScreen.PantallaPedido.name) },
                onClickVerHistorico = { navController.navigate(HamburgueseriaScreen.PantallaHistorico.name) },
                onClickVerIngredientes = { navController.navigate(HamburgueseriaScreen.PantallaIngredientes.name) },
                viewModelHamburgueseria = viewModelHamburgeseria,
                uiState = uiState
            )
        }
        composable(route = HamburgueseriaScreen.PantallaPedido.name) {
            PantallaPedido(
                viewModelHamburgeseria,
                onClickVolverPantallaPrincipal = { navController.navigate(HamburgueseriaScreen.PantallaPrincipal.name) },
                uiState
            )
        }
        composable(route = HamburgueseriaScreen.PantallaHistorico.name) {
            PantallaHistorico(
                viewModelHamburgeseria,
                onClickVolverPantallaPrincipal = { navController.navigate(HamburgueseriaScreen.PantallaPrincipal.name) },
            )

        }
        composable(route = HamburgueseriaScreen.PantallaIngredientes.name) {
            PantallaIngrediente(
                viewModelHamburgeseria,
                onClickVolverPantallaPrincipal = { navController.navigate(HamburgueseriaScreen.PantallaPrincipal.name) }
            )
        }
    }

}