package com.learning.features.domain

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

data class Note(
    val id: Long = System.currentTimeMillis(),
    val title: String="Hello Notes",
    val color: Int = Color.LightGray.toArgb(),
    val description: String="Your new notes description",
    val createdAt: Long = System.currentTimeMillis()
)