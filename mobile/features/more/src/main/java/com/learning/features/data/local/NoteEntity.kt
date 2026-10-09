package com.learning.features.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb


@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = System.currentTimeMillis(),
    val title: String="Hello Notes",
    val color: Int = Color.LightGray.toArgb(),
    val description: String="Your new notes description",
    val createdAt: Long = System.currentTimeMillis()
)
