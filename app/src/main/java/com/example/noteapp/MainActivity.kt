package com.example.noteapp

import android.os.Bundle
import android.widget.ListView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.noteapp.ui.theme.NoteAppTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val list = remember { mutableStateListOf<Objekt>() }
            NoteAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(list = list)
                }
            }
        }
    }
}

class Objekt(val title:String, val text:String)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(list: MutableList<Objekt>, modifier: Modifier=Modifier) {
    Column {
        List( list = list)

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun List(list: MutableList<Objekt>) {
    var title by rememberSaveable { mutableStateOf("") }
    var text by rememberSaveable { mutableStateOf("") }
    Column (modifier = Modifier
        .background(androidx.compose.ui.graphics.Color.Yellow)
    ){
        Column {
            TextField(value = title, onValueChange = { NewTitle ->
                title=NewTitle
            }, label = { Text(text = "Title: ")}
            )
            TextField(value = text, onValueChange = { NewText ->
                text=NewText
            }, label = { Text(text = "Text: ")}
            )

        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoteAppTheme {
    }
}