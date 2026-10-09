package com.learning.features.data

import com.learning.features.data.local.NoteEntity
import com.learning.features.data.local.ProjectEntity
import com.learning.features.data.local.ProjectHolderOwnerEntity
import com.learning.features.data.local.ProjectWithTasks
import com.learning.features.data.local.ProjectsHolderWithProjectsEntity
import com.learning.features.data.local.TaskEntity
import kotlinx.coroutines.flow.Flow


interface Repository {

    fun getAllNotes() : Flow<List<NoteEntity>>

    suspend fun saveNotes(list: List<NoteEntity>)

    suspend fun deleteNote(noteEntity: NoteEntity)

    suspend fun saveOrUpdateNote(noteEntity: NoteEntity)


    fun getAllTasks() : Flow<List<TaskEntity>>

    suspend fun saveTask(list: List<TaskEntity>)

    suspend fun deleteTask(note: TaskEntity)

    suspend fun saveOrUpdateTask(note: TaskEntity)

    suspend fun insertProject(projectEntity: ProjectEntity)

    suspend fun insertProjectHolderOwner(projectHolderOwnerEntity: ProjectHolderOwnerEntity)


    fun fetchAllProjectHolderWithProjects(): Flow<List<ProjectsHolderWithProjectsEntity>>
    fun fetchAllProjectsWithTasks(): Flow<List<ProjectWithTasks>>





}