package com.example.unitconvertor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconvertor.ui.theme.UnitConvertorTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConvertorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConvertor()
                }
            }
        }
    }
}

@Composable
fun UnitConvertor() {
    // Using remember and mutableStateOf to store and manage UI state that survives recomposition
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var conversionFactor = remember { mutableStateOf(1.00) }
    var oConversionFactor = remember { mutableStateOf(1.00) }

    fun convertUnits() {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleText("Unit Convertor")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnits()
            },
            label = { LabelText("Enter Value") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Box {
                Button(onClick = { iExpanded = true }) {
                    LabelText(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(
                        text = { LabelText("Centimeters") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimeters"
                            conversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { LabelText("Meters") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meters"
                            conversionFactor.value = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { LabelText("Feet") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { LabelText("Millimeters") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Millimeters"
                            conversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Box {
                Button(onClick = { oExpanded = true }) {
                    LabelText(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(
                        text = { LabelText("Centimeters") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Centimeters"
                            oConversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { LabelText("Meters") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Meters"
                            oConversionFactor.value = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { LabelText("Feet") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            oConversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { LabelText("Millimeters") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Millimeters"
                            oConversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        ResultText("Result: $outputValue $outputUnit")
    }
}

@Composable
fun TitleText(text: String) {
    Text(text = text, style = MaterialTheme.typography.displayMedium)
}

@Composable
fun LabelText(text: String) {
    Text(text = text, style = MaterialTheme.typography.bodyLarge)
}

@Composable
fun ResultText(text: String) {
    Text(text = text, style = MaterialTheme.typography.headlineMedium)
}

@Preview(showBackground = true)
@Composable
fun UnitConvertorPreview() {
    UnitConvertorTheme {
        UnitConvertor()
    }
}


