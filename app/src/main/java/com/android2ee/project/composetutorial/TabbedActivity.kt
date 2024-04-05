package com.android2ee.project.composetutorial

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CarCrash
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.android2ee.project.composetutor.GameContentScreen
import com.android2ee.project.composetutorial.screens.ArrayAdapterScreen
import com.android2ee.project.composetutorial.screens.BasicData
import com.android2ee.project.composetutorial.screens.EmailSetupScreen
import com.android2ee.project.composetutorial.screens.NewEmailSetupScreen
import com.android2ee.project.composetutorial.screens.RootThirdScreen
import com.android2ee.project.composetutorial.themes.MaterialColors
import com.android2ee.project.composetutorial.themes.MyCustomColors
import com.android2ee.project.composetutorial.themes.SpecificColors

// 
/** Created by Mathias Seguy also known as Android2ee on 08/03/2024.
 * The goal of this class is to :
 * to create a Tab activity with compose
 * At the bottom you have several tabs that will switch the content
 */
class TabbedActivity : ComponentActivity() {

    val viewModel: SimpleViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.init()
        setContent {
            val isSystemInDarkTheme = rememberSaveable { mutableStateOf(true) }
            val colorScheme =
                if (isSystemInDarkTheme.value) MyCustomColors.darkColorScheme else MyCustomColors.lightColorScheme
            val colorSpecific =
                if (isSystemInDarkTheme.value) MyCustomColors.darkSpecificColors else MyCustomColors.lightSpecificColors

            MaterialTheme(
                colorScheme = colorScheme
            ) {
                ContentScreen(isSystemInDarkTheme, colorSpecific)
            }
        }
    }

    @SuppressLint("UnrememberedMutableState")
    @Preview
    @Composable
    fun ContentScreePreview() {
        MaterialTheme {
            val isSystemInDarkTheme = mutableStateOf(true)
            ContentScreen(isSystemInDarkTheme, MyCustomColors.darkSpecificColors)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ContentScreen(darkThemeState: MutableState<Boolean>, specificColors: SpecificColors) {
        /**Define your tabs state and has to be retained when rotation*/
        val selectedTabs = rememberSaveable { mutableStateOf(0) }
        //then create your UI using this information
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                    title = {
                        Text("... ActionBar ... ")
                    },
                    actions = {
                        IconButton(onClick = { darkThemeState.value = !darkThemeState.value }) {
                            Icon(
                                imageVector = Icons.Filled.CarCrash,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                )
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = MaterialColors.Amber200,//MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary,
                ) {
                    BottomBarContent(selectedTabs)
                }
            }
        )
        { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(1F),
//                .background(Color.White),
                Alignment.Center
            ) {
//                BasicContent(User("Bob", "Toto"),isPressed,interactionImageIcon)
                when (selectedTabs.value) {
                    0 -> GameContentScreen(viewModel)
                    1 -> BasicData()
                    2 -> ArrayAdapterScreen(viewModel)
                    3 -> RootThirdScreen(specificColors)
                    4 -> EmailSetupScreen()
                    5 -> NewEmailSetupScreen(viewModel)
                    else -> Text(text = "0ther")
                }
            }
        }
    }

    @Composable
    fun BottomBarContent(selectedTabs: MutableState<Int>) {
        val scrollState = rememberScrollState()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { selectedTabs.value = 0 }) {
                SelectedTabText(label = "Tab 0", selectedTabs.value == 0)
            }
            Button(onClick = { selectedTabs.value = 1 }) {
                SelectedTabText(label = "Tab 1", selectedTabs.value == 1)
            }
            Button(onClick = { selectedTabs.value = 2 }) {
                SelectedTabText(label = "Tab 2", selectedTabs.value == 2)
            }
            Button(onClick = { selectedTabs.value = 3 }) {
                SelectedTabText(label = "Tab 3", selectedTabs.value == 3)
            }
            Button(onClick = { selectedTabs.value = 4 }) {
                SelectedTabText(label = "Email", selectedTabs.value == 4)
            }
            Button(onClick = { selectedTabs.value = 5 }) {
                SelectedTabText(label = "NEmail", selectedTabs.value == 5)
            }
        }
    }

    /**
     * https://developer.android.com/jetpack/compose/text/style-text
     */
    @Composable
    fun SelectedTabText(label: String, isSelected: Boolean) {
        if (isSelected) {
            Text(
                "❤\uFE0F $label ❤\uFE0F",
                color = Color.White,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
            )
        } else {
            Text(
                label,
                color = Color.Black,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Light
            )
        }
    }

}