package com.example.rireki.data.dataclass

data class ListSettings(
    val admins: List<Admin> = listOf(),
    val privacy: String = "Privat",
)