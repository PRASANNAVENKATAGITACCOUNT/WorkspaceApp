package com.learning.features.presentation.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import org.koin.androidx.compose.koinViewModel
import java.time.Month
import java.time.YearMonth


@Composable
fun CalendarUI() {
    val calendarUIViewModel = koinViewModel<CalendarUIViewModel>()

    val months = arrayOf("January", "February","March","April","May","June","July","August","September","October","November","December")
    var yearMonth by remember {
        mutableStateOf(YearMonth.now())
    }

    var currentMonth by remember {
        mutableIntStateOf(yearMonth.monthValue-1)
    }

    var monthsList by remember {
        mutableStateOf(Month.values().map { it.length(yearMonth.isLeapYear)  })
    }

    val selectedMonthDays by remember {
        derivedStateOf {
            (1..monthsList[currentMonth]).toList()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {innerPadding->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.2f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                IconButton(onClick = {
                    if(currentMonth>0){
                        currentMonth--
                    }
                }) {
                    Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "")
                }
                Text(
                    months[currentMonth],
                    style = TextStyle(),
                    )
                IconButton(onClick = {
                    if(currentMonth<11){
                        currentMonth++
                    }
                }) {
                    Icon(imageVector = Icons.AutoMirrored.Default.ArrowForward, contentDescription = "")
                }
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(5)

            ) {
                items(selectedMonthDays){
                    Button(onClick = {}) {
                        Text("$it")
                    }
                }
            }
        }
    }

}