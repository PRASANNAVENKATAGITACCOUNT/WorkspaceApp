package com.learning.features.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long=0L,
    val projectOwnerId:Long=0L,
    val taskName: String="New Task",
    val taskDescription: String="Task Description",
    val createdAt: Long = System.currentTimeMillis()
) {
}