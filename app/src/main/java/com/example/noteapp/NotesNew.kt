package com.example.noteapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import java.util.UUID
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Icon

data class Note(
    val id: String = UUID.randomUUID().toString(),
    var title: String,
    var body: String
)



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun newNote(list : MutableList<Note>, navController: NavController) {
    //var Suui by rememberSaveable { mutableStateOf("") }
    var textTitle by rememberSaveable { mutableStateOf("") }
    var textBody by rememberSaveable { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf<String?>(null) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Enter Title")
        TextField(value = textTitle, onValueChange = { newTextTitle ->
            textTitle = newTextTitle
        })
        Text(text = "Enter Note")
        TextField(value = textBody, onValueChange = { newTextBody ->
            textBody = newTextBody
        })

        Row {
            Button(onClick = {
                if (textTitle.length in 3..50 && textBody.length < 120) {
                    list.add(Note(title = textTitle, body = textBody))
                    textTitle = ""
                    textBody = ""
                } else {
                    errorMsg =
                        "Title must be between 3 and 50 characters and description must be less than 120"
                }
            }) {
                Text(text = "Create")
            }

            Button(onClick = {
                navController.navigateUp()
            }) {
                Text(text = "Home")
            }

        }
        if (errorMsg != null) {
            Text(
                text = "Title must be between 3 and 50 characters and description must be less than 120",
                style = TextStyle(
                    color = Color.Blue,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Light
                )
            )
            Button(onClick = {
                errorMsg = null
            }) {
                Text(text = "Dismiss")
            }
        }
    }
}


