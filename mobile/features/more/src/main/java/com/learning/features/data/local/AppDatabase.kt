package com.learning.features.data.local


import androidx.room.Database
import androidx.room.RoomDatabase


@Database( entities = [
    NoteEntity::class, TaskEntity::class,
    ProjectEntity::class, ProjectHolderOwnerEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getApplicationDAO(): ApplicationDAO

}