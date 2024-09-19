package com.example.hamburgueseriauistate.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.example.hamburgueseriauistate.data.DataSource
import com.example.hamburgueseriauistate.data.HamburgueseriaUIState
import com.example.hamburgueseriauistate.model.Pedido
import com.example.hamburgueseriauistate.model.Producto
import com.example.hamburgueseriauistate.model.ProductoCantidad
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.ArrayList

class HamburgueseriaModelView: ViewModel() {
    private val _uiState = MutableStateFlow(HamburgueseriaUIState())
    val uiState: StateFlow<HamburgueseriaUIState> = _uiState.asStateFlow()
    private val productos = DataSource.productos
    var historico = ArrayList<Pedido>()
    var miPedido = Pedido(mutableListOf())
    var miProducto: Producto? = null


    fun aniadirAlPedido(producto: Producto) {
        var productoEncontrado = miPedido.productos.find { it.producto.equals(producto) }

        if (productoEncontrado != null) {
            productoEncontrado.cantidad++
        }else {
            miPedido.productos.add(ProductoCantidad(producto, 1))
        }

        actualizarUiState()
    }

    fun eliminarProducto(producto: Producto) {
        var productoEncontrado = miPedido.productos.find { it.producto.equals(producto) }

        if (productoEncontrado != null) {
            miPedido.productos.remove(productoEncontrado)
        }

        actualizarUiState()
    }

    fun pagar() {

        historico.add(miPedido)

        miPedido.productos.clear()

        actualizarUiState()

    }

    private fun actualizarUiState() {
        val totalPedido = totalPedido()
        val resumenPedido = resumenPedido()

        _uiState.update { currentState ->
            currentState.copy(
                totalPedido = totalPedido,
                resumenPedido = resumenPedido
            )
        }
    }

    private fun totalPedido(): Int {
        var total = 0

        for (productoCantidad in miPedido.productos) {
            total += (productoCantidad.cantidad * productoCantidad.producto.precio)
        }

        return total
    }

    private fun resumenPedido(): String {
        var resultado = ""
        for (cadaProducto in miPedido.productos) {
            if (cadaProducto.cantidad != 0) {
                resultado += "Producto: ${cadaProducto.producto.nombre} \nprecio/unidad: ${cadaProducto.producto.precio} \ncantidad: ${cadaProducto.cantidad}\n--------------------\n"
            }
        }
        return resultado
    }

    fun elegirProducto(producto: Producto){
        miProducto = producto
    }


}