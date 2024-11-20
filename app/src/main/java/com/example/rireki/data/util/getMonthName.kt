package com.example.rireki.data.util

import java.time.LocalDate

fun getMonthName(date: LocalDate): String {
    val monthValue = date.monthValue

    val months = mapOf(
        1 to "Januar",
        2 to "Feburar",
        3 to "März",
        4 to "April",
        5 to "Mai",
        6 to "Juni",
        7 to "Juli",
        8 to "August",
        9 to "September",
        10 to "Oktober",
        11 to "November",
        12 to "Dezember"
    )

    return months.getOrDefault(monthValue, "")
}