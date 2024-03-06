package com.android2ee.project.composetutorial

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android2ee.project.composetutorial.model.User


//
/** Created by Mathias Seguy also known as Android2ee on 06/03/2024.
 * The goal of this class is to :
 *
 */
class FirstSimpleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicContent(User("Bob", "Toto"))
        }
    }

    // **************************************
    //                 UI Definition
    // **************************************
    @Composable
    fun BasicContent(user: User) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FirstRow(user)

            Spacer(modifier = Modifier.height(8.dp))

            SecondRow(user)

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(
                onClick = ::onShowToast,
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
    private fun SecondRow(user: User) {
        Row(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Cyan,
                            Color.Yellow,
                            Color.Red
                        )
                    )
                )
                .padding(12.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_android),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(36.dp)
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
        BasicContent(User("Android", "Haaze"))
    }


    // **************************************
    //                 Action
    // **************************************
    fun onShowToast() {

    }
}