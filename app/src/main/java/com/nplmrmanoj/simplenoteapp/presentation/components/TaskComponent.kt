package com.nplmrmanoj.simplenoteapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nplmrmanoj.simplenoteapp.data.model.Note
import com.nplmrmanoj.simplenoteapp.data.model.Priority
import com.nplmrmanoj.simplenoteapp.ui.theme.Blue
import com.nplmrmanoj.simplenoteapp.ui.theme.Green
import com.nplmrmanoj.simplenoteapp.ui.theme.Purple80

//@Preview(showSystemUi = true)
@Composable
fun TaskComponent(note: Note, onClick: () -> Unit) {
    
    val background = when(note.priority){

        "LOW" -> Green
        "MEDIUM" -> Blue
        "HIGH" -> Color.Red
        else -> Purple80
    }

    val (time, amPm) = getFormattedCurrentTimeParts(note.createdTime)


    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "$time\n$amPm",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 10.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .border(3.dp, color = Color.Black, shape = CircleShape),
            )

            HorizontalDivider(modifier = Modifier.width(6.dp), color = Color.Black)


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                    onClick()
                },
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier
                        .background(background, shape = RoundedCornerShape(14.dp))
                        .clip(RoundedCornerShape(14.dp))
                        .weight(0.9f)
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {

                    Text(
                        text = note.title,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                    )


                    Text(
                        text = note.description,
                        color = Color.White,
                        fontSize = 15.5.sp,
                        fontWeight = FontWeight.W400,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 4.dp).padding(end = 15.dp)
                    )

                }

                HorizontalDivider(modifier = Modifier
                    .width(6.dp)
                    .weight(0.1f), color = Color.Black)

            }

        }

    }
}
