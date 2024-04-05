package com.android2ee.project.composetutorial.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android2ee.project.composetutorial.R
import com.android2ee.project.composetutorial.model.User

// 
/** Created by Mathias Seguy also known as Android2ee on 11/03/2024.
 * The goal of this class is to :
 *
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicData() {
    val interactionImageIcon = remember { MutableInteractionSource() }
    val isPressed = interactionImageIcon.collectIsPressedAsState()
    Scaffold(
//        topBar = {
//            TopAppBar(
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primaryContainer,
//                    titleContentColor = MaterialTheme.colorScheme.primary,
//                ),
//                title = {
//                    Text("Top app bar")
//                }
//            )
//        },
//        bottomBar = {
//            BottomAppBar(
//                containerColor = MaterialTheme.colorScheme.primaryContainer,
//                contentColor = MaterialTheme.colorScheme.primary,
//            ) {
//                Text(
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    textAlign = TextAlign.Center,
//                    text = "Bottom app bar",
//                )
//            }
//        },
        floatingActionButton = {
            FloatingActionButton(onClick = { Log.e("FirstSimpleAct", "FAB clicked") }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        })
    { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            BasicContent(User("Bob", "Toto"), isPressed, interactionImageIcon)
        }
    }


}

// **************************************
//                 UI Definition
// **************************************
@Composable
fun BasicContent(
    user: User,
    isImagePressed: State<Boolean>,
    interactionImageIcon: MutableInteractionSource
) {
    val ctx = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FirstRow(user)

        Spacer(modifier = Modifier.height(8.dp))

        SecondRow(user, isImagePressed, interactionImageIcon)

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = { onShowToast(ctx) },
            modifier = Modifier
                .padding(8.dp) // margin
                .border(1.dp, Color.Yellow) // outer border
                .padding(8.dp) // space between the borders
                .border(1.dp, Color.Green) // inner border
                .padding(8.dp)
        ) {
            Text(text = "ShowToast")
        }

    }
}

@Composable
private fun FirstRow(user: User) {
    Row(
        modifier = Modifier
            .background(Color.Blue)
            .fillMaxWidth(1f)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_android),
            colorFilter = ColorFilter.tint(Color.Red),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .clip(CircleShape)
                .size(36.dp)
                .background(Color.Green)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                "Hello world! ${user.firstName}",
                fontSize = 18.sp,
                color = Color.White.copy(alpha = 0.5f),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(1f)
            )
            Text("Glad to meet you ${user.lastName}", fontSize = 12.sp, color = Color.White)
        }
    }
}


@Composable
private fun SecondRow(
    user: User,
    isImagePressed: State<Boolean>,
    interactionImageIcon: MutableInteractionSource
) {
    val currentContext = LocalContext.current
    Row(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = if (isImagePressed.value.not()) {
                        listOf(
                            Color.Cyan,
                            Color.Yellow,
                            Color.Red
                        )
                    } else {
                        listOf(
                            Color.Red,
                            Color.Yellow,
                            Color.Cyan
                        )
                    }
                )
            )
            .padding(12.dp)
    ) {
        Image(
            painter = if (isImagePressed.value.not()) painterResource(R.drawable.ic_android)
            else painterResource(R.drawable.ic_launcher_background),
            contentDescription = "Clickable Picture",
            modifier = Modifier
                .clip(CircleShape)
                .size(if (isImagePressed.value.not()) 36.dp else 96.dp)
                .clickable(
                    onClick = {
                        Toast
                            .makeText(
                                currentContext,
                                "I am the Image Toast",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    },
                    interactionSource = interactionImageIcon,
                    indication = rememberRipple(false, 102.dp, Color.Blue)
                )
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                "Hello world! ${user.firstName}",
                fontSize = 18.sp,
                color = Color.Black.copy(alpha = 0.5f)
            )
            Text(
                "Glad to meet you ${user.lastName}",
                fontSize = 12.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(8.dp) // margin
                    .border(1.dp, Color.Yellow)
            )
        }
    }
}


// **************************************
//                 UI Preview
// **************************************

@Preview
@Composable
fun BasicContentPreview() {
    val interactionImageIcon = remember { MutableInteractionSource() }
    val isPressed = interactionImageIcon.collectIsPressedAsState()
    BasicContent(User("Android", "Haaze"), isPressed, interactionImageIcon)
}


// **************************************
//                 Action
// **************************************
fun onShowToast(ctx: Context) {
    Toast.makeText(ctx, "I am the Button Toast", Toast.LENGTH_SHORT).show()
}