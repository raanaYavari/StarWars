package com.raana.starwars.ui.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.raana.starwars.ui.utils.toPx

@Composable
fun Shimmer(
    width: Dp,
    height: Dp,
    shape: Shape,
    modifier: Modifier = Modifier,
    gradientWidth: Dp = width,
) {
    Shimmer(
        width = width,
        height = height,
        gradientWidth = gradientWidth,
        shimmerColor = MaterialTheme.colors.background,
        backColor = MaterialTheme.colors.surface,
        shape = shape,
        modifier = modifier,
    )
}

@Composable
fun Shimmer(
    width: Dp,
    height: Dp,
    gradientWidth: Dp,
    shape: Shape,
    shimmerColor: Color,
    backColor: Color,
    modifier: Modifier = Modifier
) {
    val cardWidthPx = width.toPx()
    val cardHeightPx = height.toPx()
    val gWidth = gradientWidth.value

    val infiniteTransition = rememberInfiniteTransition()
    val xCardShimmer = infiniteTransition.animateFloat(
        initialValue = (cardWidthPx),
        targetValue = (-gWidth),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing,
                delayMillis = 100
            ),
            repeatMode = RepeatMode.Restart
        )
    )
    val yCardShimmer = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = (cardHeightPx + gWidth),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing,
                delayMillis = 100
            ),
            repeatMode = RepeatMode.Restart
        )
    )

    val colors = listOf(
        backColor,
        shimmerColor,
        backColor,
    )
    val brush = linearGradient(
        colors,
        start = Offset(
            xCardShimmer.value + gradientWidth.value,
            yCardShimmer.value - gradientWidth.value
        ),
        end = Offset(xCardShimmer.value, yCardShimmer.value)
    )
    Spacer(
        modifier = modifier
            .size(width, height)
            .background(brush = brush, shape = shape)
    )
}

@Preview
@Composable
fun ShimmerPreview(
) {
    Shimmer(
        width = 120.dp,
        height = 56.dp,
        shape = MaterialTheme.shapes.small,
        modifier = Modifier.fillMaxWidth()
    )
}
