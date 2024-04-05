package com.android2ee.project.composetutorial.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android2ee.project.composetutorial.R
import com.android2ee.project.composetutorial.SimpleViewModel

//
/** Created by Mathias Seguy also known as Android2ee on 08/03/2024.
 * The goal of this class is to display a list of items using the LazyColumn
 *
 */
@Composable
fun ArrayAdapterScreen(viewModel: SimpleViewModel) {
    val firstNames = viewModel.dataList.observeAsState()
    //Do A list
    if (firstNames?.value != null) {
        displayList(firstNames as State<List<String>>)
    }
}

@Composable
fun displayList(messages: State<List<String>>) {
    LazyColumn() {
        //messages.value can not be null, else you'll have an "expect Int" compilation error
        items(messages.value) { message ->
            if (message.length % 2 == 0) {
                MessageRow(message)
            } else {
                MessageRowOdd(message)
            }
        }
    }
}

@Composable
fun MessageRow(message: String) {

    Row(
        Modifier
            .fillMaxSize(1f)
            .background(Color.Green),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ic_android),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .clip(CircleShape)
                .size(36.dp)
                .background(Color.Green)
        )

        Text(
            text = message,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun MessageRowOdd(message: String) {
    Row(
        Modifier
            .fillMaxSize(1f)
            .background(Color.Yellow),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        Image(
            painter = painterResource(R.drawable.ic_android),
            colorFilter = ColorFilter.tint(Color.Red),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .clip(CircleShape)
                .size(36.dp)
                .background(Color.Green)
        )
    }
}
