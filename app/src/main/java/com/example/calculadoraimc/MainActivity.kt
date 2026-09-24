package com.example.calculadoraimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoraimc.ui.theme.CalculadoraIMCTheme
import com.example.calculadoraimc.ui.theme.calcularIMC
import com.example.calculadoraimc.ui.theme.determinarCategoriaIMC
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraIMCTheme {
                ImcScreen()
            }
        }
    }
}

@Composable
fun ImcScreen(modifier: Modifier = Modifier) {

    // -- Variáveis de estado --
    var altura by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var imc by remember { mutableStateOf(0.0) }
    var categoriaImc by remember { mutableStateOf("") }

    val corApp = colorResource(R.color.cor_app)

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // -- Header --
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = corApp)
                .statusBarsPadding()
                .height(160.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(80.dp)
                    .padding(vertical = 16.dp),
                painter = painterResource(R.drawable.bmi),
                contentDescription = "IMC",
            )

            Text(
                text = "Calculadora IMC",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        // -- Formulário --
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-30).dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF8F5F5)
                ),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Seus dados",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = corApp
                    )

                    OutlinedTextField(
                        value = altura,
                        onValueChange = { altura = it },
                        label = { Text(text = "Altura (cm)") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                    )

                    OutlinedTextField(
                        value = peso,
                        onValueChange = { peso = it },
                        label = { Text(text = "Peso (kg)") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                    )

                    Button(
                        onClick = {
                            // aceita vírgula ou ponto; ignora entrada vazia/inválida
                            val alturaDouble = altura.replace(',', '.').toDoubleOrNull()
                            val pesoDouble = peso.replace(',', '.').toDoubleOrNull()

                            if (alturaDouble != null && pesoDouble != null && alturaDouble > 0) {
                                imc = calcularIMC(
                                    altura = alturaDouble,
                                    peso = pesoDouble
                                )

                                categoriaImc = determinarCategoriaIMC(imc)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = corApp)
                    ) {
                        Text(
                            text = "CALCULAR",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // -- Card resultado --
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 32.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF5A9B6E)
                ),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    Text(
                        text = String.format(Locale.US,"%.2f", imc),
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 28.sp
                    )

                    Text(
                        text = categoriaImc,
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImcScreenPreview() {
    CalculadoraIMCTheme {
        ImcScreen()
    }
}

