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
    /* -----------     Game       ----------- */
    /* -------------------------------------- */
    val carOffSet: LiveData<IntOffset>
        get() = _carOffSet
    private var _carOffSet = MutableLiveData<IntOffset>()

    val roadOffSet: LiveData<IntOffset>
        get() = _roadOffSet
    private var _roadOffSet = MutableLiveData<IntOffset>()
    val roadMatrix: LiveData<RoadMatrix>
        get() = _roadMatrix
    private var _roadMatrix = MutableLiveData<RoadMatrix>()
    val roadMatrixBackground: LiveData<RoadMatrix>
        get() = _roadMatrixBackground
    private var _roadMatrixBackground = MutableLiveData<RoadMatrix>()
    private val roadMatrixEven: LiveData<RoadMatrix>
        get() = _roadMatrixEven
    private var _roadMatrixEven = MutableLiveData<RoadMatrix>()
    private val roadMatrixOdd: LiveData<RoadMatrix>
        get() = _roadMatrixOdd
    private var _roadMatrixOdd = MutableLiveData<RoadMatrix>()
    private var counter = 0
    private var counterMatrix = 0
    val tickle: LiveData<Boolean>
        get() = _tickle
    var _tickle = MutableLiveData<Boolean>(false)

    private val animDurationLong = 1600L
    val animDuration = 1600
    fun initGameScreen(width: Int, height: Int, density: Float) {
        screenWidth = width
        screenHeight = height
        _roadMatrixEven.value = RoadMatrix(width, height)
        _roadMatrixOdd.value = RoadMatrix(width, height)
        _roadMatrix.value = RoadMatrix(width, height)
        _roadMatrixBackground.value = RoadMatrix(width, height)

        (_roadMatrixEven.value as RoadMatrix).initializeMatrix()
        (_roadMatrixOdd.value as RoadMatrix).initializeMatrix()
        roadMatrixOdd.value?.clone(roadMatrixEven.value!!)
        (_roadMatrixOdd.value as RoadMatrix).nextLevel()

        tileW = (width / density / _roadMatrixEven.value!!.columnNumber).toInt()
//        tileH=(height/_roadMatrixEven.value!!.rowNumber).toInt()
        tileH = (height / density / _roadMatrixEven.value!!.rowNumber).toInt()

        val heightTilePx = tileH * density
        _roadOffSet.value = IntOffset(0, (-1 * heightTilePx).toInt())

        tileW = tileW

        viewModelScope.launch {
            while (true) {
//                if(_roadOffSet.value!!.y>2*heightTilePx+1){
//                    (roadMatrix.value as RoadMatrix).nextLevel()
//                }
                _roadMatrixBackground.value = RoadMatrix(width, height)
                (_roadMatrixBackground.value as RoadMatrix).clone(_roadMatrix.value as RoadMatrix)
                (_roadMatrixBackground.value as RoadMatrix).nextLevel()
                _roadMatrix.value = _roadMatrixBackground.value
                _tickle.value = true
                delay(animDurationLong)

                counterMatrix++
            }
        }
        //Log.e("ViewModel", "w=$width, h=$height, d=$density and dToInt=${density.toInt()} ")
//        viewModelScope.launch {
//            while(true){
//                //Log.e("ViewModel", "offsetY = ${_roadOffSet.value!!.y} and  tileH=$tileH tileW=$tileW")
////                if(_roadOffSet.value!!.y>2*heightTilePx+1){
////                    //reset
////                    if(counter%2==0){
////                        roadMatrix.value?.clone(roadMatrixEven.value!!)
////                        roadMatrixEven.value?.clone(_roadMatrixOdd.value!!)
//////                        roadMatrixOdd.value?.clone(roadMatrixEven.value!!)
////                        (_roadMatrixOdd.value as RoadMatrix).nextLevel()
////                    }else{
////                        roadMatrix.value?.clone(roadMatrixOdd.value!!)
//////                        roadMatrixEven.value?.clone(_roadMatrixOdd.value!!)
////                        roadMatrixOdd.value?.clone(roadMatrixEven.value!!)
////                        (roadMatrixEven.value as RoadMatrix).nextLevel()
////                    }
////                    _tickle.value=true
////                    _roadOffSet.value= IntOffset(0,-1*tileH/4)
//////                    delay(animDuration)
////                }else{
////                    _tickle.value=false
////                    _roadOffSet.value= IntOffset(0,_roadOffSet.value!!.y+(tileH/4))
////                    delay(animDuration)
////                }
////                delay(animDuration)
////                _roadOffSet.value= IntOffset(0,_roadOffSet.value!!.y+(tileH/4))
//                if(_roadOffSet.value!!.y>heightTilePx){
////                    delay(1500)
//                    (roadMatrix.value as RoadMatrix).nextLevel()
//                    _roadOffSet.value= IntOffset(0,-tileH)
////                    roadMatrix.value?.clone(roadMatrixEven.value!!)
//////                        roadMatrixEven.value?.clone(_roadMatrixOdd.value!!)
////////                        roadMatrixOdd.value?.clone(roadMatrixEven.value!!)
////                    (roadMatrixEven.value as RoadMatrix).nextLevel()
//                }else{
//                    _roadOffSet.value= IntOffset(0,_roadOffSet.value!!.y+(tileH/4))
//                }
//                delay(animDuration)
//                counter++
//            }
//        }
    }

    var tileW = 0
    var tileH = 0
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
