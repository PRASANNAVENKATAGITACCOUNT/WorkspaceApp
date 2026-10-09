package com.learning.features.domain


data class Project(
    val projectId: Long=0L,
    val projectHolderOwnerId: Long=0,
    val projectName: String="",
    val aboutProject: String=""
)

data class ProjectHolderOwner(
    val holderOwnerId: Long=0L,
    val projectStatus: String = "NOT STARTED",
)

data class ProjectsHolderWithProjects(

    val project: ProjectHolderOwner,
    val projectsList: List<Project>
)