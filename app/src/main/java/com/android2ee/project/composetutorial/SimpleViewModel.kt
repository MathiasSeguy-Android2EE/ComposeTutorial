package com.android2ee.project.composetutorial

import android.util.Log
import androidx.compose.ui.unit.IntOffset
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android2ee.project.composetutorial.model.RoadMatrix
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min

//
/** Created by Mathias Seguy also known as Android2ee on 08/03/2024.
 * The goal of this class is to :
 *
 */
class SimpleViewModel : ViewModel() {


    /* -------------------------------------- */
    /* -----------     Game Road  ----------- */
    /* -------------------------------------- */
    var tileW = 0
    var tileH = 0
    var tileHf = 0f
    val carOffSet: LiveData<IntOffset>
        get() = _carOffSet
    private var _carOffSet = MutableLiveData<IntOffset>()

    val roadOffSet: LiveData<Float>
        get() = _roadOffSet
    private var _roadOffSet = MutableLiveData<Float>()

    val roadFirstRowOffSet: LiveData<Float>
        get() = _roadFirstRowOffSet

    private var _roadFirstRowOffSet = MutableLiveData<Float>()
    val roadMatrix: LiveData<RoadMatrix>
        get() = _roadMatrix
    private var _roadMatrix = MutableLiveData<RoadMatrix>()

    private val animDurationLong = 480L
    fun initGameScreen(width: Int, height: Int, density: Float) {
        screenWidth = width
        screenHeight = height
        _roadMatrix.value = RoadMatrix(width, height)

        tileW = (width / density / _roadMatrix.value!!.columnNumber).toInt()
        tileH = (height / density / _roadMatrix.value!!.visibleRowNumber).toInt()
        tileHf = (height / _roadMatrix.value!!.visibleRowNumber).toFloat()
        val heightTilePx = tileH * density

        tileW = tileW

        var roadMatrixTemp = RoadMatrix(width, height)
        var consumed = false
        _roadOffSet.value = 0f
        viewModelScope.launch {
            while (true) {
                if (consumed) {
                    roadMatrixTemp = RoadMatrix(width, height)
                    roadMatrixTemp.clone(_roadMatrix.value as RoadMatrix)
                    roadMatrixTemp.nextLevel()
                    consumed = false
                }
                if (_roadOffSet.value!! >= heightTilePx) {
                    Log.e(
                        "AnimTrackingt",
                        "offset ${_roadOffSet.value} but height is $heightTilePx"
                    )
                    Log.e(
                        "AnimTrackingt",
                        "step= ${((heightTilePx) / animDurationLong.toFloat())} => ${animDurationLong * ((heightTilePx) / animDurationLong.toFloat())}"
                    )
                    _roadOffSet.value = 0f
                    _roadMatrix.value = roadMatrixTemp
                    consumed = true
                    delay(1)
                } else if (consumed) {
                    delay(animDurationLong)
                } else {
                    delay(1)
                }
            }
        }
        viewModelScope.launch {
            while (true) {
                _roadOffSet.value =
                    (_roadOffSet.value ?: 0f) + (2 * heightTilePx / animDurationLong.toFloat())
                _roadFirstRowOffSet.value = (_roadOffSet.value as Float) - heightTilePx
                delay(2)
            }
        }
    }

    /* -------------------------------------- */
    /* -----------     Car positionning       ----------- */
    /* -------------------------------------- */
    var carAbscissa = 0
    var carOrdinate = 0
    var screenWidth = 0
        set(value) {
            field = value
            rightMax = value / 2
            leftMin = -1 * rightMax
        }
    var leftMin = 0
    var rightMax = 0
    var screenHeight = 0
        set(value) {
            field = value
            top = value

        }
    var bottom = 0
    var top = 0
    fun moveLeft() {
        carAbscissa = max(carAbscissa - 50, leftMin)
        _carOffSet.value = IntOffset(carAbscissa, -1 * carOrdinate)
    }

    fun moveRight() {
        carAbscissa = min(carAbscissa + 50, rightMax)
        _carOffSet.value = IntOffset(carAbscissa, -1 * carOrdinate)
    }

    fun moveTop() {
        carOrdinate = min(carOrdinate + 50, top)
        _carOffSet.value = IntOffset(carAbscissa, -1 * carOrdinate)
        Log.e("ViewModel", "${_carOffSet.value}")
    }

    fun moveBottom() {
        carOrdinate = max(carOrdinate - 50, bottom)
        _carOffSet.value = IntOffset(carAbscissa, -1 * carOrdinate)
        Log.e("ViewModel", "${_carOffSet.value}")
    }

    /* -------------------------------------- */
    /* -----------     Name       ----------- */
    /* -------------------------------------- */
    data class User(val email: String, val password: String)

    fun storeUser(email: String, password: String) {
        val newUser = User(email, password)
    }

    /* -------------------------------------- */
    /* -----------     List       ----------- */
    /* -------------------------------------- */

    val dataList: LiveData<List<String>>
        get() = _dataList
    private val _dataList = MutableLiveData<List<String>>()


    fun init() {
        val list = mutableListOf<String>()
        for (i in 1..20) {
            list.add("Goretti")
            list.add("Jason")
            list.add("Alex")
            list.add("Seb")
            list.add("Patrice")
            list.add("Victor")
            list.add("Louis")
            list.add("Lucile")
            list.add("Mathias")
            list.add("Erwan")
            list.add("Ivan")
            list.add("Emilie")
            list.add("Alexandre")
            list.add("Benoit")
        }
        _dataList.value = list
        _carOffSet.value = IntOffset(0, 0)
    }
}
