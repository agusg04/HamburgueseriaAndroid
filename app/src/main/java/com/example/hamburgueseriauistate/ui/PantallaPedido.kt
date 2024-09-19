package com.example.hamburgueseriauistate.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hamburgueseriauistate.R
import com.example.hamburgueseriauistate.data.HamburgueseriaUIState
import com.example.hamburgueseriauistate.model.Pedido
import com.example.hamburgueseriauistate.model.Producto

@Composable
fun PantallaPedido(
    viewModelHamburgueseria: HamburgueseriaModelView,
    onClickVolverPantallaPrincipal: () -> Unit,
    uiState: HamburgueseriaUIState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(Color(0xFF121212)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween

    ) {

        Column {
            Cabecera(modifier = modifier, R.string.pantalla_pedido)

            Tabla(viewModelHamburgueseria.miPedido, viewModelHamburgueseria = viewModelHamburgueseria)

            Caja(modifier = modifier, uiState = uiState)
        }

        Column {
            Button(
                onClick = {viewModelHamburgueseria.pagar()},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3574E6),
                )
            ) {
                Text(
                    text = "Confirmar y pagar pedido",
                    //fontWeight = FontWeight.Bold,
                    //color = Color.White,
                    fontSize = 20.sp,
                )
            }

            Button(
                onClick = onClickVolverPantallaPrincipal,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3574E6),
                )
            ) {
                Text(
                    text = "VOLVER",
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
            modifier = modifier.padding(4.dp)
        )
    }
}

@Composable
private fun Tabla(
    pedido: Pedido?,
    viewModelHamburgueseria: HamburgueseriaModelView,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .border(
                BorderStroke(2.dp, Color(0xFF6495ED)),
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        if (pedido?.productos?.isNotEmpty() == true) {
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                //verticalArrangement = Arrangement.spacedBy(1.dp)
            ) {
                items(pedido.productos) { productocantidad ->
                    Card (
                        modifier = modifier
                            .padding(14.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E212A))
                    ){
                        Row (
                            modifier = modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Image(painter = painterResource(id = productocantidad.producto.imageResourceId), contentDescription = null)
                            Spacer(modifier = Modifier.width(15.dp))
                            Column (
                                modifier = Modifier,
                                verticalArrangement = Arrangement.spacedBy(4.dp),
                            ){
                                Text(
                                    modifier = modifier,
                                    text = productocantidad.producto.nombre,
                                    color = Color.White,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Cantidad: ${productocantidad.cantidad}",
                                    color = Color.White,
                                    fontSize = 16.sp
                                )
                                Text(
                                    text = "Precio/Unidad: ${productocantidad.producto.precio}€",
                                    color = Color.White,
                                    fontSize = 16.sp
                                )
                                Button(onClick = {viewModelHamburgueseria.eliminarProducto(productocantidad.producto)},
                                    modifier = modifier
                                        .padding(bottom = 8.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF3574E6)
                                    )
                                ) {
                                    Text(text = "Eliminar producto del pedido",
                                        color = Color.White,
                                        fontSize = 14.sp,
                                        softWrap = false,
                                        fontWeight = FontWeight.Bold
                                        )
                                }
                            }
                        }
                    }
                }
            }
        } else {
            Text(
                text = "No hay ningún producto agregado al pedido",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 16.sp,
                modifier = modifier.padding(10.dp)
            )
        }
    }
}

@Composable
private fun Caja(
    modifier: Modifier,
    uiState: HamburgueseriaUIState,
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .border(
                BorderStroke(2.dp, Color(0xFF6495ED)),
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Card (modifier = modifier
            .padding(14.dp)
            .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E212A))
        ){
            Row {
                Text(
                    text = "Total: ${uiState.totalPedido} €",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.padding(8.dp)
                )
            }
        }
    }
}