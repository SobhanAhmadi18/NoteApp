package com.example.noteapp

import ShowNote
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun noteList(noteList: MutableList<Note>, navController: NavController) {
    LazyColumn {
        item{
            Text(
                text = "My notes",
                modifier = Modifier.padding(16.dp),
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
            Button(onClick = {
                navController.navigate("newNote")
            }
            ) {
                Icon(
                    imageVector = Icons.Default.Add, contentDescription = "Add Icon"
                )
            }
        }

        items(noteList) { note->
            Column(
                modifier = Modifier
                    .clickable {
                        navController.navigate("showNote/${note.id}")
                    }
                    .padding(10.dp)
            ) {
                Divider()
                Text(
                    text = note.title,
                    modifier = Modifier.padding(16.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                )
                Text(
                    text = note.body,
                    modifier = Modifier.padding(16.dp),
                    fontSize = 20.sp,
                )

                Row {
                    Button(onClick = {
                        noteList.remove(note)
                    }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Icon")
                    }
                    Button(onClick = {
                        navController.navigate("EditNote/${note.id}")
                    }) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Icon")
                    }
                }
                Divider()
            }
        }
    }
}

@Composable
fun PreviewNoteApp() {
    val ListNote = remember { mutableStateListOf<Note>() }
    val navController = rememberNavController()

    NavHost(navController, startDestination = "notelist") {
        composable("noteList") {
            noteList(ListNote, navController)
        }
        composable("newNote") {
            newNote(ListNote, navController)
        }
        composable("EditNote/{noteId}") { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val NoteId = arguments.getString("noteId") ?: ""
            EditNote(NoteId, ListNote, navController)
        }
        composable("showNote/{noteId}") { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val NoteId = arguments.getString("noteId") ?: ""
            val note = ListNote.firstOrNull { it.id == NoteId }
            if (note != null) {
                ShowNote(note, navController)
            } else {
                null
            }
        }
    }

}