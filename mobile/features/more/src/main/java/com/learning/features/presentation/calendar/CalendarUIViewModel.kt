package com.learning.features.presentation.calendar

import androidx.lifecycle.ViewModel
import java.time.YearMonth


data class CalendarData(
    val yearMonth: YearMonth,
    val currentMonth: Int,
    val monthsList: List<Int>,
    val selectedMonthDays: List<Int>
    )



class CalendarUIViewModel : ViewModel() {


}