package com.learning.features.data.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation


data class ProjectsHolderWithProjectsEntity(
    @Embedded
    val project: ProjectHolderOwnerEntity,
    @Relation(
        parentColumn = "holderOwnerId",
        entityColumn = "projectHolderOwnerId"
    )
    val projectsList: List<ProjectEntity>
)

@Entity
data class ProjectHolderOwnerEntity(
    @PrimaryKey(autoGenerate = true)
    val holderOwnerId: Long=0L,
    val projectStatus: String = "NOT STARTED",
)

data class ProjectWithTasks(
    @Embedded
    val project: ProjectEntity,
    @Relation(
        parentColumn = "projectId",
        entityColumn = "projectOwnerId"
    )
    val projectsList: List<TaskEntity>
)


@Entity
data class ProjectEntity(
    @PrimaryKey(autoGenerate = true)
    val projectId: Long=0L,
    val projectHolderOwnerId: Long=0,
    val projectName: String="",
    val aboutProject: String=""
)