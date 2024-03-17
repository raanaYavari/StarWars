package com.raana.starwars.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.ui.graphics.vector.ImageVector

data class Info(
    val title: String,
    val value: String,
    val icon: ImageVector = Icons.Outlined.Info
)
