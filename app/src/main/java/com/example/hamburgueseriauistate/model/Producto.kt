package com.example.hamburgueseriauistate.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Producto(
    val nombre: String,
    val precio: Int,
    @DrawableRes val imageResourceId: Int,
    val ingredientes: List<Ingrediente>,
)
