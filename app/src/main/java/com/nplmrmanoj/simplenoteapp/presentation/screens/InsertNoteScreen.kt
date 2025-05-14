package com.nplmrmanoj.simplenoteapp.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nplmrmanoj.simplenoteapp.data.model.Note
import com.nplmrmanoj.simplenoteapp.presentation.components.SimpleText
import com.nplmrmanoj.simplenoteapp.presentation.components.getFormattedCurrentTimeParts
import com.nplmrmanoj.simplenoteapp.presentation.viewmodel.NoteViewModel
import com.nplmrmanoj.simplenoteapp.ui.theme.Blue
import com.nplmrmanoj.simplenoteapp.ui.theme.Green

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showSystemUi = true)
@Composable
fun InsertNoteScreen(viewModel: NoteViewModel, navController: NavController, id: Int) {

    val note by viewModel.noteById.collectAsState()


    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }

    var priority by remember { mutableStateOf<String>("LOW") }


    LaunchedEffect(key1 = id) {
        if (id != -1) {
            viewModel.getNoteByID(id)
        }
    }


    LaunchedEffect(note) {
       if (id != -1){
           note?.let {
               title = it.title
               body = it.description
               priority = it.priority
           }
       }
    }

    val currentTime = getFormattedCurrentTimeParts(System.currentTimeMillis())

    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Type Notes", fontWeight = Bold)

                        if (id == -1){
                            Text(text = "$currentTime", fontSize = 12.sp)
                        }else{
                            Text(
                                text = "Delete",
                                fontWeight = Bold,
                                fontSize = 18.sp,
                                color = Color.Red,
                                modifier = Modifier.clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ){
                                    //Delete Note
                                    viewModel.deleteNote(id)
                                    navController.popBackStack()
                                }
                            )
                        }

                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(imageVector = Icons.Sharp.ArrowBack, contentDescription = null)
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),

                actions = {
                    TextButton(
                        onClick = {
                            val note = Note(
                                title = title,
                                description = body,
                                priority = priority
                            )

                            if (id == -1) {
                                viewModel.upsertNote(note)
                            } else {
                                val existingNoteId = viewModel.noteById.value?.id ?: id
                                viewModel.updateNote(
                                    note.copy(id = existingNoteId)
                                )
                            }

                            navController.popBackStack()
                        }
                    ) {
                        Text(text = "Save", fontWeight = Bold, fontSize = 18.sp, color = Blue)
                    }

                }

            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 16.dp)
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            TextField(
                value = title,
                textStyle = TextStyle(
                    fontWeight = Bold
                ),
                onValueChange = {
                    title = it
                },
                placeholder = { Text(text = "Title", fontWeight = Bold) },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                shape = RectangleShape,
                maxLines = 1
            )


            TextField(
                value = body,
                onValueChange = {
                    body = it
                },
                placeholder = { Text(text = "Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 400.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color.Transparent
                ),
                shape = RectangleShape,
                maxLines = Int.MAX_VALUE,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Default,
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                )
            )


            Row(
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                SimpleText(
                    text = "LOW",
                    color = Green,
                    isSelected = priority == "LOW"
                ) {
                    priority = "LOW"
                }

                SimpleText(
                    text = "MEDIUM",
                    color = Blue,
                    isSelected = priority == "MEDIUM"
                ) {
                    priority = "MEDIUM"
                }

                SimpleText(
                    text = "HIGH",
                    color = Color.Red,
                    isSelected = priority == "HIGH"
                ) {
                    priority = "HIGH"
                }

            }
        }
    }
}

