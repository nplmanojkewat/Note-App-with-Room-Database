package com.nplmrmanoj.simplenoteapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nplmrmanoj.simplenoteapp.R
import com.nplmrmanoj.simplenoteapp.data.model.Note
import com.nplmrmanoj.simplenoteapp.presentation.components.TaskComponent
import com.nplmrmanoj.simplenoteapp.presentation.navigation.InsertNote
import com.nplmrmanoj.simplenoteapp.presentation.viewmodel.NoteViewModel
import com.nplmrmanoj.simplenoteapp.ui.theme.Green

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showSystemUi = true)
@Composable
fun MainScreen(
    viewModel: NoteViewModel,
    mainActivity: MainActivity,
    navController: NavController
) {

    var notes by remember { mutableStateOf(listOf<Note>()) }
    viewModel.getNotes().observe(mainActivity){
        notes = it
    }

//    var isShowProfile by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Simple Notes", fontWeight = FontWeight.Bold)
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
//                            isShowProfile = true
                        },
                        modifier = Modifier.clip(CircleShape)
                    ) {
                        Image(painter = painterResource(R.drawable.profile), contentDescription = null)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(InsertNote(id = -1))
                },
                containerColor = Green
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(26.dp)

        ) {


            Column(
                modifier = Modifier.padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Hi ")
                        withStyle(
                            style = SpanStyle(
                                color = Green
                            )
                        ) {
                            append("Manoj!")
                        }
                    },
                    fontSize = 25.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = Color.Blue
                            ),
                        ){
                            append("")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                            )
                        ) {
                            append("Tasks for you Sweety!")
                        }
                    }

                )

            }


            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(notes){ note ->
                    TaskComponent(
                        note,
                        onClick = {
                            navController.navigate(InsertNote(note.id))
                        },
                    )
                }
            }
        }


//        if (isShowProfile){
//
//            Dialog(
//                onDismissRequest = {
//                    isShowProfile = false
//                },
//            ) {
//                Card(
//                    modifier = Modifier.size(220.dp, 240.dp).padding(12.dp),
//                    shape = RoundedCornerShape(12.dp),
//                    colors = CardDefaults.cardColors(containerColor = Color.White)
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.showprofile),
//                        contentDescription = null,
//                        contentScale = ContentScale.Fit,
//                        modifier = Modifier.fillMaxSize()
//                    )
//                }
//            }
//
//        }


    }

}