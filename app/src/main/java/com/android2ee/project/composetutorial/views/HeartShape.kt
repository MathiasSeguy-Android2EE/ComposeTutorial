package com.android2ee.project.composetutorial.views

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.LayoutDirection
import kotlin.math.min


object HeartShape : CornerBasedShape(
    topStart = CornerSize(0f),
    topEnd = CornerSize(0f),
    bottomEnd = CornerSize(0f),
    bottomStart = CornerSize(0f)
) {
    fun createPath(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Float
    ): Path {
        val path = Path()
        val cornerRadius = min(size.width, size.height) / 2f
        path.addArc(
            Rect(
                0f,
                0f,
                (size.width / 2f) + cornerRadius,
                size.height / 2f + cornerRadius
            ),
//            Rect(
//                size.width / 2f - cornerRadius,
//                0f,
//                (size.width / 2f) + cornerRadius,
//                size.height / 2f + cornerRadius
//            ),
            0f,
            180f
        )
        path.arcTo(
            Rect(
                size.width / 2f,
                0f,
                size.width,
                size.height / 2f + cornerRadius
            ),
            0f,
            180f,
            false
        )
        path.lineTo(size.width / 2f, size.height)
        path.close()
        return path
    }


    override fun copy(
        topStart: CornerSize,
        topEnd: CornerSize,
        bottomEnd: CornerSize,
        bottomStart: CornerSize
    ): CornerBasedShape {
        TODO("Not yet implemented")
    }

    override fun createOutline(
        size: Size,
        topStart: Float,
        topEnd: Float,
        bottomEnd: Float,
        bottomStart: Float,
        layoutDirection: LayoutDirection
    ): Outline {
        val path = createPath(size, layoutDirection, 1.0f)
        return Outline.Generic(path)
    }
}