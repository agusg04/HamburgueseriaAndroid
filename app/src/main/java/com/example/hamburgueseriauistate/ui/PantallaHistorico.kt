package com.example.hamburgueseriauistate.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hamburgueseriauistate.R
import com.example.hamburgueseriauistate.model.Pedido
import java.util.ArrayList

@Composable
fun PantallaHistorico(
    viewModelHamburgueseria: HamburgueseriaModelView,
    onClickVolverPantallaPrincipal: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(Color(0xFF121212)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Cabecera(modifier = modifier, R.string.pantalla_historico)

            Tabla(viewModelHamburgueseria.historico, viewModelHamburgueseria)
        }

        Column {
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
fun Tabla(
    historico: ArrayList<Pedido>,
    viewModelHamburgueseria: HamburgueseriaModelView,
    modifier: Modifier = Modifier
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
        if (historico.size > 0) {
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
            ) {
                items(historico) { pedido ->
                    PedidoCard(pedido)
                }
            }
        }
    }
}

@Composable
fun PedidoCard(
    pedido: Pedido,
    modifier: Modifier = Modifier
){
    Card (
        modifier = modifier
            .padding(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E212A))
    ){
        Row (
            modifier = modifier
                .fillMaxWidth(),
        ){
            Column {
                pedido.productos.forEach{ productoCantidad ->
                    Text(text = "${productoCantidad.producto} cant: ${productoCantidad.producto.precio}\n",
                        color = Color.White,
                        fontSize = 14.sp,
                        )
                }
                Text("Total: ${calcularTotal(pedido)} €",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                    )
            }
        }
        }
}

private fun calcularTotal(
    pedido: Pedido

): Int {
    var total: Int = 0

    for (producto in pedido.productos){
        total += (producto.cantidad * producto.producto.precio)

    }
    return total

}