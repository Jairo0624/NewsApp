package com.example.newsapp

import android.R.attr.value
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun NewsPage(innerPadding : PaddingValues) {
    var texto by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                innerPadding
            )
            .padding(15.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedTextField(
                modifier = Modifier.weight(4f),
                value = texto,
                onValueChange = { escrito ->
                    texto = escrito
                },
                placeholder = {
                    Text(
                        text = "Buscar",
                        fontWeight = FontWeight.Bold
                    )
                },

                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Icono de búsqueda" // Para accesibilidad
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            Arrangement.SpaceBetween
        )
        {
            Text(
                text = "Noticias",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.drawBehind {
                    val strokeWidth = 4.dp.toPx()
                    val y = size.height
                    drawLine(
                        color = Color.Blue,         // Color del subrayado
                        start = Offset(0f, y),
                        end = Offset(size.width, y),
                        strokeWidth = strokeWidth
                    )
                })

            Text(
                text = "Eventos",
                fontSize = 25.sp,
                color = Color.LightGray,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Clima",
                fontSize = 25.sp,
                color = Color.LightGray,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            text = "Ultimas noticias",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 10.dp)
        )

        val tasks = remember {
            mutableStateListOf<String>("El presidente de Estados Unidos no muestra arrepentimiento con sus decisiones",
                "Tesla podria estar presentando su nueva version de su vehiculo automata (hace mas que manejarse solo)")
        }
        val fechas = remember {
            mutableStateListOf<String>("febrero 08 - 2024", "febrero 14 - 2024")
        }


        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(tasks) { index, task ->
                NewsItem(
                    title = task,
                    date = fechas[index]
                )
            }

        }

        Text(
            text = "Alrededor del Mundo",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 10.dp)
        )

        val noticias = listOf(
            "Cae la inflación en enero y da señales de recuperación.",
            "Nuevo récord en bolsa tecnológica.",
            "Avances médicos prometen esperanza para pacientes crónicos.",
            "El turismo en México crece un 20% en 2024.",
            "Descubren exoplaneta similar a la Tierra.",
            "Gigantes Tecnologicos envian Robots a Marte"
        )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(noticias.size) { index ->
                    NewsCard(noticias[index])
                }
            }

    }
}

@Composable
fun NewsItem(title: String, date: String) {
        Card(
            modifier = Modifier
                .fillMaxHeight(0.3f) // alto aprox 30% de pantalla
                .width(250.dp), // ancho = 3/4 de la pantalla
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF5D00E0)),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 22.sp,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = date,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
}

@Composable
fun NewsCard(title: String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFB7B7B7))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                maxLines = 4, 
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
@Preview(
    showBackground = true,
    showSystemUi = true
)

@Composable
fun HomeScreenPreview(){
    NewsPage(innerPadding = PaddingValues(0.dp))
}