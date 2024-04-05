package com.android2ee.project.composetutorial.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.android2ee.project.composetutorial.SimpleViewModel

// 
/** Created by Mathias Seguy also known as Android2ee on 15/03/2024.
 * The goal of this class is to :
 *
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewEmailSetupScreen(viewModel: SimpleViewModel) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp, 5.dp)
//            .background(Color.White)
    ) {


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
//                .background(Color.White)
                .fillMaxWidth(1f)
                .padding(5.dp, 5.dp)
        ) {
            Text(
                "You can configure your email in just few steps"
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
//                .background(Color.White)
                .fillMaxWidth(1f)
                .padding(0.dp, 5.dp)
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth(1f),
                label = { Text("Email Address") }
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
//                .background(Color.White)
                .fillMaxWidth(1f)
                .padding(5.dp, 5.dp)
        ) {
            TextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth(1f),
//                colors = TextFieldDefaults.textFieldColors(
//                    containerColor = Color(0x00FF00FF)),
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
        }

        Spacer(
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth(1f)
//                .background(Color.White)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
//                .background(Color.White)
                .fillMaxWidth(1f)
                .padding(5.dp, 5.dp)
        ) {
            OutlinedButton(onClick = { onNext(email, password, viewModel) }) {
                Text("Next")
            }
        }
    }
}

fun onNext(email: String, password: String, viewModel: SimpleViewModel) {
    viewModel.storeUser(email, password)
}