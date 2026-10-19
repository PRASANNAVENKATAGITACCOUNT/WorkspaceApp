package com.learning.features.presentation.calendar

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.selects.select
import java.time.Month
import java.time.YearMonth
import kotlin.compareTo
import kotlin.dec


data class CalendarData (
    val yearMonth: YearMonth = YearMonth.now(),
    val currentMonth: Int,
    val monthsList: List<Int>
    ) {
    companion object{
        fun setMonthsList() : List<Int> = Month.values().map { it.length(YearMonth.now().isLeapYear)  }
    }
}





class CalendarUIViewModel : ViewModel() {

    private var _calendarData : MutableStateFlow<CalendarData> =  MutableStateFlow(
        CalendarData(
            currentMonth = YearMonth.now().monthValue-1,
            monthsList = CalendarData.setMonthsList()
        )
    )

    val calendarData = _calendarData.asStateFlow()


    fun incrementMonth(){
        var currentMonth = _calendarData.value.currentMonth
        if (currentMonth >= 11) return
        currentMonth++
        _calendarData.update { data ->
            data.copy(currentMonth = currentMonth, monthsList = data.monthsList)
        }
    }


    fun decrementMonth() {
        var currentMonth = _calendarData.value.currentMonth
        if(currentMonth<=0) return
        currentMonth--
        _calendarData.update { data ->
            data.copy(currentMonth = currentMonth, monthsList = data.monthsList)
        }
    }






}