package com.android2ee.project.composetutor


import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.android2ee.project.composetutorial.R
import com.android2ee.project.composetutorial.SimpleViewModel
import com.android2ee.project.composetutorial.model.RoadMatrix
import com.android2ee.project.composetutorial.themes.MaterialColors
import kotlin.math.roundToInt

// 
/** Created by Mathias Seguy also known as Android2ee on 25/03/2024.
 * The goal of this class is to :
 *
 */
@Composable
fun GameContentScreen(viewModel: SimpleViewModel) {

    val configuration = LocalConfiguration.current

    val screenWidth = with(LocalDensity.current) {
        configuration.screenWidthDp.dp.toPx().roundToInt()
    }
    val screenHeight = with(LocalDensity.current) {
        configuration.screenHeightDp.dp.toPx().roundToInt()
    }

    viewModel.initGameScreen(screenWidth, screenHeight, LocalDensity.current.density)
    GameObject.simpleVM = viewModel
    displayContent(
        offSetState = viewModel.carOffSet.observeAsState(),
        roadOffSetState = viewModel.roadOffSet.observeAsState(),
        viewModel.roadMatrix.observeAsState(),
        viewModel.roadMatrixBackground.observeAsState(),
        viewModel.tileW,
        viewModel.tileH,
        viewModel.animDuration,
        viewModel.tickle.observeAsState()
    )
}

@Composable
fun displayContent(
    offSetState: State<IntOffset?>,
    roadOffSetState: State<IntOffset?>,
    roadMatrix: State<RoadMatrix?>,
    roadMatrixBackground: State<RoadMatrix?>,
    tileW: Int,
    tileH: Int,
    animDuration: Int,
    tickle: State<Boolean?>
) {
    val offset by animateIntOffsetAsState(
        targetValue = offSetState.value ?: IntOffset(0, 0),
        label = "offset",
        animationSpec = tween(durationMillis = 500, easing = LinearEasing)
    )
    val roadOffsetPipo = if (tickle.value ?: false) {
        animateIntOffsetAsState(
            targetValue = IntOffset(0, (tileH * LocalDensity.current.density).toInt()),
            label = "offset",
            animationSpec = tween(durationMillis = 500, easing = LinearEasing)
        )
    } else {
        animateIntOffsetAsState(
            targetValue = offSetState.value ?: IntOffset(0, 0),
            label = "offset",
            animationSpec = tween(durationMillis = 500, easing = LinearEasing)
        )
    }

//    val roadOffset by animateIntOffsetAsState(
//        targetValue = roadOffSetState.value ?: IntOffset(0, 0),
//        label = "roadoffset",
//        animationSpec = tween(
//            durationMillis = if (roadOffSetState.value!!.y.toFloat()>0) {
//                animDuration
//            } else {
//                0
//            }, easing = LinearEasing
//        )
//    )
    val infiniteScrolling = rememberInfiniteTransition(label = "Scrolling")


    val roadOffset by infiniteScrolling.animateFloat(
        initialValue = 0f,
        targetValue = tileH * LocalDensity.current.density,
        animationSpec = infiniteRepeatable(
            animation = tween(animDuration, easing = LinearEasing)
        ),
        label = ""
    )

    Log.e("TrackingAnim", "Tickle value is ${tickle.value}")
    var modifierMM = Modifier
        .fillMaxWidth(1f)
        .alpha(1f)
        .graphicsLayer {
            translationY = if (tickle.value ?: false) roadOffset else {
                0f
            }
        }

    roadMatrix.value

//    var modifierMM = if (tickle.value == false) {
//        Modifier
//            .fillMaxWidth(1f)
//            .alpha(1f)
//            .graphicsLayer {
//                translationY = if(roadOffset.y.toFloat()>0){
//                    roadOffset.y.toFloat()
//                }else{
//                    0f
//                }
//            }
//    } else {
//        Modifier
//            .fillMaxWidth(1f)
//            .alpha(1f)
//    }

    Box(Modifier.fillMaxSize()) {
        //the road
        val columns = roadMatrix.value?.columnNumber ?: 0
        val rows = roadMatrix.value?.rowNumber ?: 0
        var tileValue = 0
        //Log.e("RoadMatrix", "matrix size in screen |is col=$columns row=$rows")
//        Column(
//            modifier = Modifier
//                .background(MaterialColors.Green800)
//                .fillMaxWidth()
//        ) {
//            for (i in 0..2) {
//                Row(modifier =  Modifier
//                    .fillMaxWidth(1f)) {
//                    for (j in 0..columns) {
//                        tileValue=(roadMatrixBackground.value?.matrix?.get(i)?.get(j)?:0)
////                        Log.e("RoadMatrix","adding cell $i $j ${roadMatrix.value?.matrix?.get(i)?.get(j) ?: 0}")
//                        if (tileValue > 99) {
//                            drawField( tileW, tileH,tileValue)
//                        } else {
//                            drawRoad( tileW, tileH,tileValue)
//                        }
//                    }
//                }
//            }
//        }
        Column(
            modifier = modifierMM
//            modifier = Modifier
//                .fillMaxWidth(1f)
//                .alpha(1f)
        ) {
            for (i in 0..rows) {
                Row {
                    for (j in 0..columns) {
                        tileValue = (roadMatrix.value?.matrix?.get(i)?.get(j) ?: 0)
//                        Log.e(
//                            "RoadMatrix",
//                            "adding cell $i $j ${roadMatrix.value?.matrix?.get(i)?.get(j) ?: 0}"
//                        )
                        if (tileValue > 99) {
                            drawField(tileW, tileH, tileValue)
                        } else {
                            drawRoad(tileW, tileH, tileValue)
                        }
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .background(MaterialColors.Green800)
                .wrapContentWidth(Alignment.Start)
        ) {
            for (i in 0..rows) {
                Row(
                    modifier = Modifier.wrapContentWidth(Alignment.Start)
                ) {

                    Text(
                        "${i}",
                        modifier = Modifier
                            .background(if ((i % 2) == 0) MaterialColors.Grey700 else MaterialColors.Green800)
                            .height(tileH.dp)
                    )
                }
            }
        }
        //the car
        Image(painterResource(R.drawable.ic_car),
            contentDescription = "car",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp, 83.dp)
                .graphicsLayer {
                    translationX = offset.x.toFloat()
                    translationY = offset.y.toFloat()
                }
                .align(Alignment.BottomCenter)
        )
        ControlsButtons()
    }

}

@Composable
private fun drawRoad(
    tileW: Int, tileH: Int, tileValue: Int
) {
//    Text(
//        "${roadMatrix.value?.matrix?.get(i)?.get(j)}",
//        modifier = Modifier
//            .background(MaterialColors.Grey700)
//            .size(tileW.dp, tileH.dp)
//    )

    val drawable = when (tileValue) {
        0, 1 -> R.drawable.ic_road1
        else -> R.drawable.ic_road0
    }
    Image(
        painterResource(drawable),
        contentDescription = "road0",
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(tileW.dp, tileH.dp)
    )
}

@Composable
private fun drawField(
    tileW: Int, tileH: Int, tileValue: Int
) {

    val drawable = when (tileValue) {
        100, 101 -> R.drawable.ic_field0
        else -> R.drawable.ic_field1
    }
    Image(
        painterResource(drawable),
        contentDescription = "field0",
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(tileW.dp, tileH.dp)
    )
}

@Composable
fun ControlsButtons() {
    Box(
        Modifier.fillMaxSize()
    ) {
        Row(modifier = Modifier.align(Alignment.BottomEnd)) {
            FilledTonalButton(
                onClick = { GameObject.left() },
                modifier = Modifier
                    .align(Alignment.Top)
                    .padding(15.dp, 0.dp, 15.dp, 0.dp)
            ) {
                Text("<-")
            }
            FilledTonalButton(
                onClick = { GameObject.right() },
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .padding(15.dp, 0.dp, 15.dp, 0.dp)
            ) {
                Text("->")
            }
        }

        Column(modifier = Modifier.align(Alignment.BottomStart)) {

            FilledTonalButton(
                onClick = { GameObject.top() }, modifier = Modifier.align(Alignment.Start)
            ) {
                Text("\u2191")
            }
            FilledTonalButton(
                onClick = { GameObject.bottom() }, modifier = Modifier.align(Alignment.End)
            ) {
                Text("\u2193")
            }
        }
    }
}

object GameObject {
    var simpleVM: SimpleViewModel? = null


    fun left() {
        simpleVM?.moveLeft()
    }

    fun right() {
        simpleVM?.moveRight()
    }

    fun top() {
        simpleVM?.moveTop()
    }

    fun bottom() {
        simpleVM?.moveBottom()
    }

}