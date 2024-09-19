package com.example.hamburgueseriauistate.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.hamburgueseriauistate.R
import com.example.hamburgueseriauistate.data.DataSource
import com.example.hamburgueseriauistate.data.HamburgueseriaUIState
import com.example.hamburgueseriauistate.model.Ingrediente
import com.example.hamburgueseriauistate.model.Producto

@Composable
fun PantallaPrincipal(
    modifier: Modifier = Modifier,
    productos: ArrayList<Producto> = DataSource.productos,
    onClickVerPedido: () -> Unit,
    onClickVerHistorico: () -> Unit,
    onClickVerIngredientes: () -> Unit,
    viewModelHamburgueseria: HamburgueseriaModelView,
    uiState: HamburgueseriaUIState
){
    Column (
        modifier = modifier
            .background(Color(0xFF121212)),
        verticalArrangement = Arrangement.SpaceBetween,
    ){

        Column (horizontalAlignment = Alignment.CenterHorizontally){

            Cabecera(modifier, R.string.titulo_principal)

            ProductosScroll(
                modifier,
                productos,
                viewModelHamburgueseria,
                onClickVerIngredientes
            )

            Column (modifier = modifier.padding(start = 30.dp, end = 30.dp, top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally){
                Card (colors = CardDefaults.cardColors(containerColor = Color(0xFF1E212A))){
                    Text(text = "Clica sobre un producto par ver sus ingredientes",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = modifier
                            .background(Color(0xFF1E212A))
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
                /*Text(text = "Clica sobre un producto par ver sus ingredientes",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .background(Color(0xFF1E212A))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp))
                )*/
            }

            LazyColumn (modifier = modifier
                .padding(top = 10.dp)
                .width(350.dp)
                .height(280.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                item { Text(text = uiState.resumenPedido,
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    modifier = modifier
                        .background(Color(0xFF1E212A))
                        .padding(12.dp)
                        .fillMaxWidth()
                ) }
            }

            /*Column (modifier = modifier
                .padding(top = 10.dp)
                .width(350.dp),
                horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = uiState.resumenPedido,
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    modifier = modifier
                        .background(Color(0xFF1E212A))
                        .padding(12.dp)
                        .fillMaxWidth()
                )
            }*/
        }

        Column {
            Button(onClick = onClickVerPedido,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3574E6),
                )) {
                Text(
                    text = "Pedido actual",
                    //fontWeight = FontWeight.Bold,
                    //color = Color.White,
                    fontSize = 18.sp,
                )

            }

            Button(onClick = onClickVerHistorico,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3574E6),
                )) {
                Text(
                    text = "Histórico pedidos",
                    //fontWeight = FontWeight.Bold,
                    //color = Color.White,
                    fontSize = 18.sp,
                )

            }
        }

    }

}

@Composable
private fun Cabecera(modifier: Modifier, texto: Int){
    Column (
        modifier = modifier
            .height(50.dp)
            .fillMaxWidth()
            .background(
                Color(0xFF1E212A)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = stringResource(id = texto),
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 20.sp,
        )
    }
}

@Composable
private fun ProductosScroll(
    modifier: Modifier,
    productos: ArrayList<Producto>,
    viewModelHamburgueseria: HamburgueseriaModelView,
    onClickVerIngredientes: () -> Unit
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .height(300.dp)
            .fillMaxWidth()
    ) {
        items(productos) {producto ->
            Card (
                modifier = modifier
                    .padding(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1E212A)
                ),
                border = BorderStroke(1.dp, Color(0xFF6495ED))
            ) {

               Box(
                   modifier = modifier.align(Alignment.CenterHorizontally)
               ){
                   Image(
                       painter = painterResource(id = producto.imageResourceId),
                       contentDescription = null,
                       modifier = Modifier.clickable { viewModelHamburgueseria.elegirProducto(producto)
                           onClickVerIngredientes()}
                   )
               }

                Text(text = producto.nombre,
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .padding(10.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Text(text = "${producto.precio} €",
                    color = Color.White,
                    fontSize = 18.sp,
                    modifier = modifier
                        .padding(4.dp)
                        .align(Alignment.CenterHorizontally)
                    )

                Column (modifier = modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Button(
                        onClick = { viewModelHamburgueseria.aniadirAlPedido(producto) },
                        modifier = modifier
                            .padding(bottom = 3.dp)
                            .width(160.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF3574E6),
                        )
                    ) {
                        Text(text = "AÑADIR")
                    }

                    Button(onClick = { viewModelHamburgueseria.elegirProducto(producto)
                                     onClickVerIngredientes()},
                        modifier = modifier
                            .padding(bottom = 6.dp)
                            .width(160.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF3574E6),
                        )
                    ) {
                        Text(text = "INGREDIENTES")
                    }
                }
            }
        }
    }
}
