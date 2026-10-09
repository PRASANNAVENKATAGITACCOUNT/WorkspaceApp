package com.learning.features.data.local

import com.learning.features.data.Repository
import kotlinx.coroutines.flow.Flow

class LocalRepository(val appDatabase: AppDatabase) : Repository {

    val applicationDAO = appDatabase.getApplicationDAO()

    override fun getAllNotes(): Flow<List<NoteEntity>> {
        return applicationDAO.getAllNotes()
    }

    override suspend fun saveNotes(list: List<NoteEntity>) {
        applicationDAO.insertNotes(list)
    }

    override suspend fun deleteNote(noteEntity: NoteEntity) {
        applicationDAO.deleteNote(noteEntity.id)
    }

    override suspend fun saveOrUpdateNote(noteEntity: NoteEntity) {
        applicationDAO.insertNote(noteEntity)
    }



    override fun getAllTasks(): Flow<List<TaskEntity>> {
        return applicationDAO.getAllTasks()
    }

    override suspend fun saveTask(list: List<TaskEntity>) {
        applicationDAO.insertTasks(list)
    }

    override suspend fun deleteTask(note: TaskEntity) {
        applicationDAO.deleteTask(note.id)
    }

    override suspend fun saveOrUpdateTask(taskEntity: TaskEntity) {
        applicationDAO.insertTask(taskEntity)
    }

    override suspend fun insertProject(projectEntity: ProjectEntity) {
        applicationDAO.insertProject(projectEntity)
    }

    override suspend fun insertProjectHolderOwner(projectHolderOwnerEntity: ProjectHolderOwnerEntity) {
        applicationDAO.insertProjectHolderOwner(projectHolderOwnerEntity)
    }

    override  fun fetchAllProjectHolderWithProjects(): Flow<List<ProjectsHolderWithProjectsEntity>> {
        return applicationDAO.getProjectHolderWithProjects()
    }

    override  fun fetchAllProjectsWithTasks(): Flow<List<ProjectWithTasks>> {
        return applicationDAO.getProjectsWithTasks()
    }


}