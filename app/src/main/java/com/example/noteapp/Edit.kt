

package com.example.noteapp

import android.provider.ContactsContract
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
//hh
//https://chat.openai.com/share/6c916a5a-e5e1-4ea7-8ea5-73cbeb8c4d01
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNote(noteId: String, list: MutableList<Note>, navController: NavController) {

    val note = list.firstOrNull { it.id == noteId}
    var errorMsg by remember { mutableStateOf<String?>(null) }
    if (note != null) {
        var textTitle by remember { mutableStateOf(note.title) }
        var textBody by remember { mutableStateOf(note.body) }

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
                    if(textTitle.length < 50 && textTitle.length > 3 && textBody.length < 120) {
                        note.title = textTitle
                        note.body = textBody
                        val noteIndex = list.indexOfFirst { it.id == noteId }
                        list[noteIndex] = note
                        navController.navigateUp()
                    }
                    else{
                        errorMsg = "Title must be between 50 and 3 characters, description must be less than 120"
                    }

                }) {
                    Text(text = "Save")
                }

                Button(onClick = {
                    navController.navigateUp()
                }) {
                    Text(text = "Cancel")
                }
            }
            if(errorMsg!=null){
                Row {
                    Button(onClick = {
                        errorMsg = null
                    }) {
                        Text(
                            text = "Dismiss",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
                Column {
                    Text(
                        text = errorMsg!!,
                        style = TextStyle(
                            color = Color.Blue,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Light,
                        )
                    )
                }

            }
        }
    } 
}

