package com.learning.features.data

import com.learning.features.data.local.NoteEntity
import com.learning.features.data.local.ProjectEntity
import com.learning.features.data.local.ProjectHolderOwnerEntity
import com.learning.features.data.local.TaskEntity
import com.learning.features.domain.Note
import com.learning.features.domain.Project
import com.learning.features.domain.ProjectHolderOwner
import com.learning.features.domain.Task


fun TaskEntity.toTask(): Task {
    return Task(
        id=this.id,
        taskName = this.taskName,
        taskDescription  =this.taskDescription,
        createdAt=this.createdAt
    )
}

fun Task.toTaskEntity(): TaskEntity {
    return TaskEntity(
        id=this.id,
        taskName = this.taskName,
        taskDescription  =this.taskDescription,
        createdAt=this.createdAt
    )
}

fun NoteEntity.toNote(): Note {
    return Note(
        id=this.id,
        title=this.title,
        color=this.color,
        description=this.description,
        createdAt=this.createdAt
    )
}
fun Note.toNoteEntity(): NoteEntity{
    return NoteEntity(
        id=this.id,
        title=this.title,
        color=this.color,
        description=this.description,
        createdAt=this.createdAt
    )
}

fun ProjectEntity.toProject(): Project {
    return Project(
        projectId=this.projectId,
        projectHolderOwnerId=this.projectHolderOwnerId,
        projectName = this.projectName,
        aboutProject=this.aboutProject
    )
}

fun Project.toProjectEntity(): ProjectEntity{
    return ProjectEntity(
        projectId=this.projectId,
        projectHolderOwnerId=this.projectHolderOwnerId,
        projectName = this.projectName,
        aboutProject=this.aboutProject
    )
}

fun ProjectHolderOwnerEntity.toProjectHolderOwner(): ProjectHolderOwner {
    return ProjectHolderOwner(
        holderOwnerId=this.holderOwnerId,
        projectStatus = this.projectStatus
    )
}
fun ProjectHolderOwner.toProjectHolderOwnerEntity(): ProjectHolderOwnerEntity {
    return ProjectHolderOwnerEntity(
        holderOwnerId=this.holderOwnerId,
        projectStatus = this.projectStatus
    )
}


