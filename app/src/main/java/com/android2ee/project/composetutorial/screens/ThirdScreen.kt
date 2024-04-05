package com.android2ee.project.composetutorial.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.android2ee.project.composetutorial.R
import com.android2ee.project.composetutorial.themes.MyCustomColors
import com.android2ee.project.composetutorial.themes.SpecificColors

// 
/** Created by Mathias Seguy also known as Android2ee on 12/03/2024.
 * The goal of this class is to :
 *
 */
@Composable
fun RootThirdScreen(specificColors: SpecificColors) {
    val visibility = remember { mutableStateOf(false) }
    val sliderPosition = remember { mutableFloatStateOf(0f) }
    var emptyBullet = (sliderPosition.value * 100).toInt()
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(specificColors.skyColor),
        horizontalAlignment = Alignment.CenterHorizontally
        //            .animateContentSize()
    ) {
        SharedPrefsToggle(
            text = "Hello",
            value = visibility.value,
            onValueChanged = { it -> visibility.value = it })
        SliderMinimalExample(sliderPosition)
        ShowGrid(emptyBullet, 100 - emptyBullet, visibility.value, specificColors)
        PlayWithAnimation(10, visibility.value, specificColors)
    }
}

@Composable
fun SharedPrefsToggle(
    text: String,
    value: Boolean,
    onValueChanged: (Boolean) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(text)
        Checkbox(checked = value, onCheckedChange = onValueChanged)
    }
}

@Composable
fun SliderMinimalExample(sliderPosition: MutableFloatState) {
    Column {
        Slider(
            value = sliderPosition.value,
            onValueChange = { sliderPosition.value = it }
        )
        Text(text = "Speed = ${(sliderPosition.value * 100).toInt()} km/h")
    }
}

@Composable
@Preview
fun previewGrid() {
    ShowGrid(emptyBullet = 18, fullBullets = 22, true, MyCustomColors.darkSpecificColors)
}

@Composable
fun AnimateBackgroundColor() {
    var animateBackgroundColor = remember {
        mutableStateOf(true)
    }
    LaunchedEffect(Unit) {
        animateBackgroundColor.value = true
    }
    // [START android_compose_animate_background_color]
    val animatedColor = animateColorAsState(
        if (animateBackgroundColor.value) Color.Green else Color.Blue,
        label = "color"
    )
    Column(
        modifier = Modifier.drawBehind {
            drawRect(animatedColor.value)
        }
    ) {
        // your composable here
    }
    // [END android_compose_animate_background_color]
}

@Composable
fun ShowGrid(
    emptyBullet: Int,
    fullBullets: Int,
    visibility: Boolean,
    specificColors: SpecificColors
) {

    val firstAnimatedColor = animateColorAsState(
        if (visibility) specificColors.sunlightOverTheTop else specificColors.sunlightTop,
        label = "color",
        animationSpec = tween(durationMillis = 3000, easing = FastOutSlowInEasing)
    )
    val secondAnimatedColor = animateColorAsState(
        if (visibility) specificColors.sunlightTop else specificColors.sunlightMiddle,
        label = "color",
        animationSpec = tween(durationMillis = 3000, easing = FastOutSlowInEasing)
    )
    val thirdAnimatedColor = animateColorAsState(
        if (visibility) specificColors.sunlightMiddle else specificColors.sunlightBottom,
        label = "color",
        animationSpec = tween(durationMillis = 3000, easing = FastOutSlowInEasing)
    )
    var index = 0
    var rowIndex = 0
    val rowMaxSize = 10
    val bulletsNumber = emptyBullet + fullBullets
    Column(
        Modifier
            .fillMaxWidth()
            .drawBehind {
                drawCircle(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            firstAnimatedColor.value,
                            secondAnimatedColor.value,
                            thirdAnimatedColor.value
                        )
                    ),
                    radius = 256f
                )
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        while (index < bulletsNumber) {
            Spacer(modifier = Modifier.padding(4.dp))
            rowIndex = 0
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                Row(
                    Modifier.fillMaxWidth(0.75f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    while (rowIndex < rowMaxSize && index < bulletsNumber) {
                        if (100 - index < emptyBullet) {
                            Image(
                                painter = painterResource(R.drawable.ic_android),
                                contentDescription = "Contact profile picture",
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(16.dp)
                                    .background(specificColors.sunlightBottom)
                            )
                        } else {
                            Image(
                                painter = painterResource(R.drawable.ic_android),
                                contentDescription = "Contact profile picture",
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(16.dp)
                                    .background(specificColors.sunlightOverTheTop)
                            )
                        }
                        index++
                        rowIndex++
                    }
                }
            }
            index++
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PlayWithAnimation(speed: Int, visibility: Boolean, specificColors: SpecificColors) {
    val density = LocalDensity.current
    AnimatedVisibility(
        visible = visibility,
        enter = slideInVertically {
            // Slide in from 40 dp from the top.
            with(density) { -40.dp.roundToPx() }
        } + expandVertically(
            // Expand from the top.
            expandFrom = Alignment.Top
        ) + fadeIn(
            // Fade in with the initial alpha of 0.3f.
            initialAlpha = 0.3f
        ),
        exit = shrinkVertically() + fadeOut()//slideOutVertically()// +
    ) {
        // Fade in/out the background and the foreground.{
        Row(
            Modifier
                .sizeIn(minWidth = 256.dp, minHeight = 0.dp)
                .background(specificColors.earthcolor),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.ic_android),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(18.dp, 256.dp)
                    .background(Color.Transparent)
            )
        }
    }
}
