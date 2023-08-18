package com.taweewong.coverqr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.taweewong.coverqr.ui.theme.CoverQRTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoverQRTheme {
                var number by remember { mutableStateOf("") }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Row {
                        Text(text = number)
                    }
                    Row {
                        Button(content = { Text(text = "1") }, modifier = Modifier.padding(14.dp), onClick = { number += 1 })
                        Button(content = { Text(text = "2") }, modifier = Modifier.padding(14.dp), onClick = { number += 2 })
                        Button(content = { Text(text = "3") }, modifier = Modifier.padding(14.dp), onClick = { number += 3 })
                    }
                    Row {
                        Button(content = { Text(text = "4") }, modifier = Modifier.padding(14.dp), onClick = { number += 4 })
                        Button(content = { Text(text = "5") }, modifier = Modifier.padding(14.dp), onClick = { number += 5 })
                        Button(content = { Text(text = "6") }, modifier = Modifier.padding(14.dp), onClick = { number += 6 })
                    }
                    Row {
                        Button(content = { Text(text = "7") }, modifier = Modifier.padding(14.dp), onClick = { number += 7 })
                        Button(content = { Text(text = "8") }, modifier = Modifier.padding(14.dp), onClick = { number += 8 })
                        Button(content = { Text(text = "9") }, modifier = Modifier.padding(14.dp), onClick = { number += 9 })
                    }
                    Row {
                        Button(content = { Text(text = "x") }, modifier = Modifier.padding(14.dp), onClick = { number = number.dropLast(1) })
                        Button(content = { Text(text = "0") }, modifier = Modifier.padding(14.dp), onClick = { number += 0 })
                        Button(content = { Text(text = "ok") }, modifier = Modifier.padding(14.dp), onClick = { })
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoverQRTheme {
        Greeting("Android")
    }
}