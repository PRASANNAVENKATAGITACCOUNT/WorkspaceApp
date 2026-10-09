package com.learning.features.presentation


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEach

@Composable
fun MoreScreen(navigateToFeature: (String) -> Unit) {
    val features= APP_FEATURES.entries
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {innerPadding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TODO: Recently used feature
            LazyRow(
               modifier =  Modifier
            ) {

            }
            // App Features
            features.fastForEach { feature->
                AppFeatureUI(feature.featureName){featureName->
                    navigateToFeature.invoke(featureName)
                }
            }
        }
    }
}

@Composable
fun AppFeatureUI(featureName: String,navigateToFeature:(String)->Unit={}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(10.dp)
            .clickable {
                navigateToFeature.invoke(featureName)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "")
        Text(
            text = featureName,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(start = 5.dp)
        )
    }
}

enum class APP_FEATURES(val featureName: String){
    WORKSPACE_FEATURE("Workspace"),
    PROJECT_FEATURE("Projects"),
    TO_DO_LIST_FEATURE("To Do List"),
    NOTES_FEATURE("Notes"),
}

@Preview(showBackground = true)
@Composable
fun MoreScreenPreview() {
    MoreScreen{}
}


sealed class FEATURE_SCREEN(val featureRoute: String){

    object AUTH_SCREEN: FEATURE_SCREEN("AUTH_SCREEN")
    object MAIN_DASHBOARD_SCREEN : FEATURE_SCREEN("MAIN_SCREEN")
    object PROJECT_FEATURE_SCREEN: FEATURE_SCREEN("PROJECT_FEATURE_SCREEN")
    object TODO_FEATURE_SCREEN: FEATURE_SCREEN("TODO_FEATURE_SCREEN")
    object NOTES_FEATURE_SCREEN: FEATURE_SCREEN("NOTES_FEATURE_SCREEN")

}