package com.learning.features.domain



data class Task(
    val id: Long=0L,
    val taskName: String="New Task",
    val taskDescription: String="Task Description",
    val createdAt: Long = System.currentTimeMillis()
)