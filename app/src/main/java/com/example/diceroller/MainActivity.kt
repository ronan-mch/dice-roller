package com.example.diceroller

import android.os.Bundle
import android.widget.Spinner
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType


import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                DiceRollerApp()
            }
        }

    }
}

@Preview
@Composable
fun DiceRollerApp(){
    DiceWithButtonAndImage(
        Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun DiceWithButtonAndImage(modifer: Modifier = Modifier){
    var result by remember { mutableStateOf(1) }
    var sideInput by remember { mutableStateOf("6") }

    Column(
        modifier = modifer,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Enter number of sides"
        )

        EditNumberField(
            value = sideInput,
            onValueChange = { sideInput = it }
        )

        Spacer(Modifier.height(16.dp))

        Button(onClick = { result = roll(sideInput) }) {
            Text(stringResource(R.string.roll))
        }
        Spacer(Modifier.height(16.dp))
        Text(text = result.toString())
    }
}

@Composable
fun EditNumberField(
    value: String, onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = "Sides" )},
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}


fun roll(sideInput: String): Int {
    val ceiling = parseSideInput(sideInput)
    return (1..ceiling).random()
}

fun parseSideInput(sideInput: String): Int {
    return try {
        sideInput.toInt()
    } catch (e: NumberFormatException) {
        6
    }
}