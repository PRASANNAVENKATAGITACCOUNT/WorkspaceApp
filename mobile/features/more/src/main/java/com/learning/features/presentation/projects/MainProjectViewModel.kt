package com.learning.features.presentation.projects

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.features.data.Repository
import com.learning.features.data.local.ProjectHolderOwnerEntity
import com.learning.features.data.local.ProjectWithTasks
import com.learning.features.data.local.ProjectsHolderWithProjectsEntity
import com.learning.features.data.toProjectEntity
import com.learning.features.domain.Project
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class MainProjectViewModel (val repository: Repository) : ViewModel() {

    private val _project : MutableStateFlow<Project?> = MutableStateFlow(null)

    val project = _project.asStateFlow()

    private val _projectHolderWithProjects : MutableStateFlow<List<ProjectsHolderWithProjectsEntity>> = MutableStateFlow(emptyList())

    val projectHolderWithProject = _projectHolderWithProjects.asStateFlow()


    private val _projectHolderOwnerEntity: MutableStateFlow<ProjectHolderOwnerEntity> = MutableStateFlow(
        ProjectHolderOwnerEntity(
            projectStatus = "NOT STARTED"
        )
    )
    val projectHolderOwner = _projectHolderOwnerEntity.asStateFlow()



    private val _projectsWithTasks : MutableStateFlow<List<ProjectWithTasks>?> = MutableStateFlow(null)
    val projectsWithTasks = _projectsWithTasks.asStateFlow()


    init {
        viewModelScope.launch {
            fetchAllProjects();
        }
    }

    private suspend fun fetchAllProjects() {
        repository.fetchAllProjectHolderWithProjects().collectLatest {
            if(it.isNotEmpty()){
                _projectHolderOwnerEntity.value = it[0].project
            }
            _projectHolderWithProjects.value = it
        }
        repository.fetchAllProjectsWithTasks().collectLatest {
            _projectsWithTasks.value = it
        }

    }


    fun updateProject(project: Project){
        _project.value = project
    }

    fun moveProjectToNewProjectHolder(projectId:Long,newProjectHolder: Long){
        _projectHolderWithProjects.value.forEach { (_, projectsList) ->
            var found = false
            for(p in projectsList){
                if(p.projectId == projectId){
                    found=true
                    viewModelScope.launch {
                        val updatedProject= p.copy(projectHolderOwnerId = newProjectHolder)
                        repository.insertProject(updatedProject)
                    }
                    break
                }
            }
            if(found) return@forEach
        }
    }


    fun insertProject(){
        val project = _project.value?: return
        viewModelScope.launch {
            repository.insertProject(project.toProjectEntity())
            _project.update { p -> Project() }
        }
    }

    fun createProjectHolderOwner(){
        val projectHolderOwner = projectHolderOwner.value.copy(holderOwnerId = 0L)
        viewModelScope.launch {
            repository.insertProjectHolderOwner(projectHolderOwner)
        }
    }

    fun updateProjectHolderOwner(project: ProjectHolderOwnerEntity){
        _projectHolderOwnerEntity.value = project
    }


}