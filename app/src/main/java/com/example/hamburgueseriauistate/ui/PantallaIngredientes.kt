package com.example.hamburgueseriauistate.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hamburgueseriauistate.R
import com.example.hamburgueseriauistate.model.Ingrediente
import com.example.hamburgueseriauistate.model.Producto

@Composable
fun PantallaIngrediente(
    viewModelHamburgueseria: HamburgueseriaModelView,
    onClickVolverPantallaPrincipal: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .background(Color(0xFF121212)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween

    ){
        Column {
            Cabecera(modifier = modifier, R.string.pantalla_ingredientes)

            Tabla(viewModelHamburgueseria.miProducto)
        }

        Button(onClick = onClickVolverPantallaPrincipal,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3574E6),
            )) {
            Text(
                text = "VOLVER",
                //fontWeight = FontWeight.Bold,
                //color = Color.White,
                //fontSize = 20.sp,
                )

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
private fun Tabla(
    producto: Producto?,
    modifier: Modifier = Modifier
    ) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .border(
                BorderStroke(2.dp, Color(0xFF6495ED))
            )
    ) {
        if (producto != null) {
            Row (modifier = modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 4.dp),
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = producto.nombre,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(producto.ingredientes) { ingrediente ->
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp),
                        //horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "-> ${ingrediente.nombre}",
                            color = Color.White,
                            fontSize = 16.sp,
                            modifier = modifier.padding(10.dp)
                        )
                    }
                }
            }
        } else {
            Text(text = "Error, no hay producto que mostrar")
        }
    }
}