package com.android2ee.project.composetutorial.model

import kotlin.random.Random


//
/** Created by Mathias Seguy also known as Android2ee on 26/03/2024.
 * The goal of this class is to represent a road as a matrix
 *  0, 0 , 1 ,1 ,1 ,1 ,0 ,0
 *  0, 0 , 1 ,1 ,1 ,1 ,0 ,0
 *  0, 0 , 1 ,1 ,1 ,1 ,0 ,0
 *  0, 0 , 1 ,1 ,1 ,1 ,0 ,0
 *  0, 0 , 1 ,1 ,1 ,1 ,0 ,0
 *  0, 0 , 1 ,1 ,1 ,1 ,0 ,0
 *  0, 0 , 1 ,1 ,1 ,1 ,0 ,0
 *  0, 0 , 1 ,1 ,1 ,1 ,0 ,0
 *  0, 0 , 1 ,1 ,1 ,1 ,0 ,0
 *  0, 0 , 1 ,1 ,1 ,1 ,0 ,0
 *  and the goal is to be able to make it scrollable
 */
class RoadMatrix(val width: Int, val height: Int) {

    var columnNumber: Int = 0
    var rowNumber: Int = 0

    /** matrix[row][col] */
    var matrix: Array<Array<Int>>

    init {
        //initialize the
        columnNumber = width / 48
        rowNumber = height / 83
        matrix = Array(rowNumber + 1) {
            Array(columnNumber + 1) {
                0 //default constructor
            }
        }
    }

    fun initializeMatrix() {
        for (j in 1..columnNumber) {
            matrix[0][j] = getRoadTile()
        }
        for (j in 1..3) {
            matrix[0][j] = getFieldTile()
        }
        for (j in columnNumber downTo (columnNumber - 2)) {
            matrix[0][j] = getFieldTile()
        }
        for (row in 1..rowNumber) {//TODO adapy
            for (col in 1..columnNumber) {
                if (matrix[row - 1][col] == 1) {
                    matrix[row][col] = getRoadTile()
                }
            }
        }
    }

    fun nextLevel() {
        var matrixCopi: Array<Array<Int>> = Array(rowNumber + 1) {
            Array(columnNumber + 1) {
                getFieldTile() //default constructor
            }
        }
        //Recopie
        for (row in 0..rowNumber) {//TODO adapy
            for (col in 0..columnNumber) {
                matrixCopi[row][col] = matrix[row][col]
            }
        }

        //Recopie
        for (row in 1..rowNumber) {//TODO adapy
            for (col in 0..columnNumber) {
                matrix[row][col] = matrixCopi[row - 1][col]
            }
        }

        //find first index and last index of 1
        //allow to change the first index from 1 to 0
        //allow to change the last index from 1 to 0 only if lastIndex-firstIndex < 6
        var firstIndex = 0
        var currentIndex = 0
        val roadWidth = 8
        //generate first line again
        for (col in 1..columnNumber) {
            //cases on the border o shrink
            if (isField(matrixCopi[0][col - 1]) && !isField(matrixCopi[0][col])) {
                firstIndex = col
            }
            matrix[0][col] = matrixCopi[0][col]
        }
        //ne fait que tourner a droite
        //je vais tout droit ou a gauche
        when (Random.nextInt(0, 5)) {
            0 -> if (firstIndex != 0) firstIndex--
            4 -> if (firstIndex <= columnNumber - roadWidth) firstIndex++
        }
        for (col in 0..columnNumber) {
            if (col < firstIndex) {
                matrix[0][col] = getFieldTile()
            } else if (col <= firstIndex + roadWidth) {
                matrix[0][col] = getRoadTile()
            } else {
                matrix[0][col] = getFieldTile()
            }
//            matrix[1][col]=matrix[0][col]
        }
    }

    fun clone(src: RoadMatrix) {
        //Recopie
        for (row in 0..rowNumber) {//TODO adapy
            for (col in 0..columnNumber) {
                matrix[row][col] = src.matrix[row][col]
            }
        }
    }

    fun getFieldTile(): Int {
        return Random.nextInt(100, 112)
    }

    fun isField(value: Int): Boolean {
        return value > 99
    }

    fun getRoadTile(): Int {
        return Random.nextInt(0, 12)
    }
}