package com.android2ee.project.composetutorial

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import com.android2ee.project.composetutorial.screens.BasicData


//
/** Created by Mathias Seguy also known as Android2ee on 06/03/2024.
 * The goal of this class is to :
 *
 */
@OptIn(ExperimentalMaterial3Api::class)
class FirstSimpleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                BasicData()
            }
        }
    }

}