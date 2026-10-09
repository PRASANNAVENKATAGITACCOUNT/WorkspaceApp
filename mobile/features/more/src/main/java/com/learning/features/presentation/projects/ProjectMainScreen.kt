package com.learning.features.presentation.projects

import android.content.ClipData
import android.content.ClipDescription
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.mimeTypes
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import com.learning.features.data.local.ProjectEntity
import com.learning.features.data.local.ProjectHolderOwnerEntity
import com.learning.features.data.local.ProjectsHolderWithProjectsEntity
import kotlinx.coroutines.CoroutineScope

@Composable
fun ProjectMainScreen(
    listOfProjectOwners: List<ProjectsHolderWithProjectsEntity> = emptyList(),
    navigateToCreateProject:()-> Unit={},
    navigateToEditProject: (Long) -> Unit={},
    navigateToCreateProjectHolderOwner:()-> Unit={},
    moveProjectToNewProjectHolderOwner:(projectId: Long, newProjectHolderOwnerId:Long)-> Unit={ _, _->},
    listState: LazyListState = rememberLazyListState(),
    coroutineScope : CoroutineScope = rememberCoroutineScope()
    ) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {innerPadding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(innerPadding)
        ) {
            Text(
                text = "Projects",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                ),
                modifier = Modifier.padding(5.dp)
            )
            Text(
                text = "Manage and track Projects here.",
                style = TextStyle(
                    fontSize = 15.sp
                ),
                modifier = Modifier.padding(5.dp)
            )

            CustomMaterialButton(modifier = Modifier.padding(8.dp).fillMaxWidth(0.6f),"Create Project Holder ",navigateToCreateProjectHolderOwner)

            LazyRow(
                modifier = Modifier.fillMaxSize(),
                state=listState
            ) {
                items(
                    listOfProjectOwners,
                    key = { it.project.holderOwnerId }) { projectHolderWithOwner ->
                    ProjectHolderOwner(
                        projectHolderWithOwner,
                        navigateToCreateProject = {
                            navigateToCreateProject.invoke()
                        },
                        navigateToEditProject = {
                            navigateToEditProject.invoke(it)
                        }, moveProjectToNewOwner = { projectId, newProjectHolder ->
                            moveProjectToNewProjectHolderOwner.invoke(projectId, newProjectHolder)
                        }, autoScrollOnDrag = { scrollOnLeftSide, scrollOnRightSide ->
//                        if(scrollOnLeftSide){
//                            coroutineScope.launch {
//                                listState.animateScrollToItem(listOfProjectOwners.size-1)
//                            }
//                        }
                        })
                }
            }
        }
    }
}

@Composable
private fun CustomMaterialButton(modifier: Modifier= Modifier.height(45.dp), text:String, navigateToCreateProjectHolderOwner: () -> Unit) {
    Button(
        onClick = {
            navigateToCreateProjectHolderOwner.invoke()
        }, modifier = modifier
            .clip(RoundedCornerShape(8.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = text)
        }
    }
}

@Composable
fun ProjectHolderOwner(projectHolder: ProjectsHolderWithProjectsEntity,
                       navigateToCreateProject: () -> Unit={},
                       navigateToEditProject:(Long)-> Unit={},
                       moveProjectToNewOwner:(projectId: Long, newProjectHolderOwnerId:Long)-> Unit={ _, _->},
                       autoScrollOnDrag:(Boolean, Boolean)-> Unit={_,_->}
) {
    Card(
        modifier = Modifier
            .width(250.dp)
            .wrapContentHeight()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .dragAndDropTarget(
                shouldStartDragAndDrop = { event ->
                    event.mimeTypes().contains(ClipDescription.MIMETYPE_TEXT_PLAIN)
                },
                target = object : DragAndDropTarget {
                    override fun onDrop(event: DragAndDropEvent): Boolean {
                        val clipData = event.toAndroidDragEvent().clipData
                        clipData?.let { data ->
                            if (clipData.itemCount > 0) {
                                val text = clipData.getItemAt(0).text.toString()
                                val ids = text.split(",")
                                moveProjectToNewOwner.invoke(
                                    ids[1].toLong(),
                                    projectHolder.project.holderOwnerId
                                )
                                return true
                            }
                        }
                        return false
                    }

                    override fun onMoved(event: DragAndDropEvent) {
                        val androidEvent = event.toAndroidDragEvent()
                        val currentX = androidEvent.x
                        val leftEdgeThreshold = 50f
                        autoScrollOnDrag(currentX <= leftEdgeThreshold, false)
                    }

                }
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = projectHolder.project.projectStatus)
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                items(projectHolder.projectsList, key = {it.projectId}){project->
                    ProjectUI(project){
                        navigateToEditProject.invoke(project.projectId)
                    }
                }
                item {
                    CustomMaterialButton(Modifier.padding(8.dp).fillMaxWidth().height(35.dp),"Create Project",navigateToCreateProject)
                }
            }
        }
    }
}

@Composable
fun ProjectUI(projectEntity: ProjectEntity,
              navigateToCreateEditProject:()-> Unit={}) {
    val textMeasurer = rememberTextMeasurer()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(5.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable {
                    navigateToCreateEditProject.invoke()
                }
                .background(color = Color.White)
                .dragAndDropSource(
                    drawDragDecoration = {
                        drawRoundRect(
                            color = Color.White,
                            size = size,
                            cornerRadius = CornerRadius(10f)
                        )

                        drawText(
                            textMeasurer = textMeasurer,
                            text = projectEntity.projectName,
                            topLeft = Offset(24f, 24f)
                        )
                    }
                ) { offset ->
                    DragAndDropTransferData(
                        ClipData.newPlainText(
                            "text",
                            "${projectEntity.projectName},${projectEntity.projectId},${projectEntity.projectHolderOwnerId}"
                        )
                    )
                }
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = projectEntity.projectName,
                style = TextStyle(
                    fontSize = 15.sp
                ),
                modifier = Modifier.padding(6.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProjectMainScreenPreview() {
    val projectEntityHoldersWithProjects = listOf<ProjectsHolderWithProjectsEntity>(
        ProjectsHolderWithProjectsEntity(
            project = ProjectHolderOwnerEntity(),
            projectsList = listOf(
                ProjectEntity(projectName = "Build Chat App"),
                ProjectEntity(projectName = "New Project")
            )
        ),
        ProjectsHolderWithProjectsEntity(
            project = ProjectHolderOwnerEntity(
                projectStatus = "IN PROGRESS"
            ),
            projectsList = listOf(
                ProjectEntity(projectName = "Build Business Management App"),
                ProjectEntity(projectName = "New Project")
            )
        ),
        ProjectsHolderWithProjectsEntity(
            project = ProjectHolderOwnerEntity(
                projectStatus = "DONE"
            ),
            projectsList = listOf(
                ProjectEntity(projectName = "New Project"),
            )
        ),
    )
        ProjectMainScreen(
            listOfProjectOwners = projectEntityHoldersWithProjects
        )


}