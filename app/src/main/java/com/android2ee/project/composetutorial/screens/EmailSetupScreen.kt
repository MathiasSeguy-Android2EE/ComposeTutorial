package com.android2ee.project.composetutorial.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 
/** Created by Mathias Seguy also known as Android2ee on 15/03/2024.
 * The goal of this class is to :
 *
 */
@Composable
fun EmailSetupScreen() {
    //use the Column (vertical layout)
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        //Use a Row to display the Title (we could have used a Box)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth(1f)
                .padding(0.dp, 5.dp)
        ) {
            Text(
                "Email Setup",
                fontSize = 32.sp
            )
        }
        //Use a Row to display the sentence (we could have used a Box)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
//                .background(Color.White)
                .fillMaxWidth(1f)
                .padding(0.dp, 5.dp)
        ) {
            Text(
                "You can configure your email in just few steps",
                fontSize = 16.sp
            )
        }

        //Use a Row to display on the same line the Label and the EditText
        //so called Text and TextField in compose
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
//                .background(Color.White)
                .fillMaxWidth(1f)
                .padding(0.dp, 5.dp)
        ) {
            //Simple Text for the label constrained in size
            Text(
                "Email address:",
                modifier = Modifier
                    .fillMaxWidth(0.33f)
                    .padding(start = 0.dp, top = 0.dp, end = 10.dp, bottom = 0.dp),
                textAlign = TextAlign.End
            )
            //Simple text for the label also constrained in size
            OutlinedTextField(
                value = "text@gmail.com",
                onValueChange = { updateEmailAddress(it) },
                modifier = Modifier.fillMaxWidth(0.75f)
            )
        }

        //Use a Row to display on the same line the Label and the EditText
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
//                .background(Color.White)
                .fillMaxWidth(1f)
                .padding(0.dp, 5.dp)
        ) {
            Text(
                "Password :",
                modifier = Modifier
                    .fillMaxWidth(0.33f)
                    .padding(start = 0.dp, top = 0.dp, end = 10.dp, bottom = 0.dp),
                textAlign = TextAlign.End
            )
            OutlinedTextField(//password visible
                value = " ",
                onValueChange = { updateEmailAddress(it) },
                modifier = Modifier.fillMaxWidth(0.5f)
            )
        }
        //use a Spacer to add some space between components
        Spacer(
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth(1f)
//                .background(Color.White)
        )
        //add the button in a the last line with an Arrangement.End
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
//                .background(Color.White)
                .fillMaxWidth(1f)
                .padding(start = 0.dp, top = 0.dp, end = 10.dp, bottom = 0.dp)
        ) {
            OutlinedButton(onClick = { onNext() }) {
                Text("Next")
            }
        }
    }
}

@Preview
@Composable
fun previewSetup() {
    EmailSetupScreen()
}

fun updateEmailAddress(address: String) {

}

fun onNext() {

}