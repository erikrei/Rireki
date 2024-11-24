package com.example.rireki.data

import java.time.LocalDate

data class ProfileList(
    val id: String = "",
    val name: String = "",
    val createdFrom: String = "",
    val createdAt: LocalDate = LocalDate.now(),
    val profiles: List<Profile> = listOf()
)
