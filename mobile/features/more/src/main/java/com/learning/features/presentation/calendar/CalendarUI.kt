package com.learning.features.presentation.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.learning.features.presentation.calendar.Common.monthsHeadingNames
import com.learning.features.ui.theme.ComposeNewAppTheme
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth
import kotlin.compareTo
import kotlin.dec


@Composable
fun CalendarUI() {
    val calendarUIViewModel = koinViewModel<CalendarUIViewModel>()
    val calendarData by calendarUIViewModel.calendarData.collectAsStateWithLifecycle()
    val selectedMonthDays by remember {
        derivedStateOf {
            (1..calendarData.monthsList[calendarData.currentMonth]).toList()
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                NavigationMonthUI(Icons.AutoMirrored.Default.ArrowBack) {
                    calendarUIViewModel.decrementMonth()
                }
                Text(
                    text = monthsHeadingNames[calendarData.currentMonth],
                    style = TextStyle(),
                )

                NavigationMonthUI(Icons.AutoMirrored.Default.ArrowForward) {
                    calendarUIViewModel.incrementMonth()
                }
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(5)
            ) {
                items(selectedMonthDays, key = {it}) {
                    val isCurrentDay = (it == LocalDate.now().dayOfMonth && (calendarData.currentMonth + 1 == calendarData.yearMonth.monthValue))
                    CalendarDayUI(isCurrentDay, it) { }
                }
            }
        }
    }

}

@Composable
private fun NavigationMonthUI(imageVector: ImageVector, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        ElevatedCard(
            modifier = Modifier
                .wrapContentSize()
                .padding(8.dp)
                .clip(CircleShape)
        )
        {
            Icon(imageVector = imageVector, contentDescription = "")
        }
    }
}

@Composable
fun CalendarDayUI(isCurrentMonthDay: Boolean, day: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(5.dp)
            .clip(RectangleShape)
            .border(
                width = if (isCurrentMonthDay) 2.dp else 0.dp,
                if (isCurrentMonthDay) Color.Black else Color.White,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Text(text = "$day", style = TextStyle(fontSize = 18.sp))
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewCalendar(modifier: Modifier = Modifier) {
    ComposeNewAppTheme {
        CalendarUI()
    }
}