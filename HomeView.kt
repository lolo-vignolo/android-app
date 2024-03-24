package com.example.descuentosapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.descuentosapp.components.Alert
import com.example.descuentosapp.components.MainButton
import com.example.descuentosapp.components.MainTextField
import com.example.descuentosapp.components.SpaceH
import com.example.descuentosapp.components.TwoCards


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun HomeView() {

   Scaffold(
       topBar = {
           CenterAlignedTopAppBar(
                title = { Text(text = "Descuentos App", color = Color.White , ) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF43A047),


                )
            )

       }
   ) {
    HomeViewContent(it)
   }
}

@Composable
fun HomeViewContent(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(10.dp)
            .fillMaxSize(),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //formulario
        var precio by remember { mutableStateOf("") }
        var descuento by remember { mutableStateOf("") }
        //botones
        var precioDescuento by remember { mutableStateOf(0.0) }
        var totalDescuento by remember { mutableStateOf(0.0) }
        //alert
        var showAlert by remember { mutableStateOf(false) }
        
        TwoCards(title1 ="Total" , number1 = totalDescuento  , title2 = "Descuento" , number2 = precioDescuento)
        SpaceH()
        MainTextField(label = "precio", value = precio , onValueChange = { precio = it })
        MainTextField(label = "descuento", value = descuento , onValueChange = { descuento = it })
        SpaceH(20.dp)
        MainButton(text = "clacular descuento", onClick = {
            if(precio.isEmpty() || descuento.isEmpty()){
                showAlert = true
            } else {
                precioDescuento = calcularDescuento(precio.toDouble(), descuento.toDouble())
                totalDescuento = calcularTotal(precio.toDouble(), descuento.toDouble())
            }}, color= Color(0xFF43A047))
        MainButton(text = "limpiar", onClick = {
            precio = ""
            descuento = ""
            precioDescuento = 0.0
            totalDescuento = 0.0
        } , color = Color.Red)
        if(showAlert){
            Alert(
                Title = "Error",
                message = "Por favor ingrese los datos",
                confirmText = "Aceptar",
                onConfirmClick = { showAlert = false },
                onDimissClick = { showAlert = false }
            )
        }
    }
}

fun calcularDescuento(precio: Double, descuento: Double): Double {
    return precio * descuento / 100
}

fun calcularTotal(precio: Double, descuento: Double): Double {
    return precio - calcularDescuento(precio, descuento)
}

