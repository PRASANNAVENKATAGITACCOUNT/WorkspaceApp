package com.learning.features.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface ApplicationDAO {

    @Upsert
    suspend fun insertNotes(noteEntities: List<NoteEntity>)

    @Upsert
    suspend fun insertNote(noteEntity: NoteEntity)

    @Query("DELETE FROM NOTEENTITY")
    suspend fun deleteAllNotes()

    @Query("DELETE FROM NOTEENTITY WHERE id =:noteId")
    suspend fun deleteNote(noteId: Long)

    @Query("SELECT * FROM NOTEENTITY")
    fun getAllNotes(): Flow<List<NoteEntity>>


    // TASKS

    @Insert
    suspend fun insertTasks(notes: List<TaskEntity>)

    @Upsert
    suspend fun insertTask(note: TaskEntity)

    @Query("DELETE FROM TaskEntity WHERE id =:noteId")
    suspend fun deleteTask(noteId: Long)


    @Query("DELETE FROM TaskEntity")
    suspend fun deleteAllTask()

    @Query("SELECT * FROM TaskEntity")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Upsert
    suspend fun insertProject(projectEntity: ProjectEntity)


    @Transaction
    @Query("SELECT * FROM PROJECTHOLDEROWNERENTITY")
    fun getProjectHolderWithProjects(): Flow<List<ProjectsHolderWithProjectsEntity>>

    @Transaction
    @Query("Select * from ProjectEntity")
    fun getProjectsWithTasks(): Flow<List<ProjectWithTasks>>

    @Upsert
    suspend fun insertProjectHolderOwner(projectHolderOwnerEntity: ProjectHolderOwnerEntity)



}