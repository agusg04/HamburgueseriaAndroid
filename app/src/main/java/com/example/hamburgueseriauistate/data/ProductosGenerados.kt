package com.example.hamburgueseriauistate.data

import com.example.hamburgueseriauistate.R
import com.example.hamburgueseriauistate.model.Ingrediente
import com.example.hamburgueseriauistate.model.Producto

object DataSource {

    private val lechuga= Ingrediente("Lechuga")
    private val tomate = Ingrediente("Tomate")
    private val pollo = Ingrediente("Pollo")
    private val ternera = Ingrediente("Ternera")
    private val queso = Ingrediente("Queso")

    val productos = arrayListOf<Producto>(
        Producto("Hamburguesa Simple", 8, R.drawable.pollo , listOf(lechuga, tomate, pollo )),
        Producto("Hamburguesa Doble", 12, R.drawable.doble, listOf(lechuga, tomate, ternera)),
        Producto("Hamburguesa Queso", 12, R.drawable.queso, listOf(lechuga, tomate, ternera, queso)),
        )

}