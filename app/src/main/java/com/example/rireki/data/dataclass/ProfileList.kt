package com.example.rireki.data.dataclass

data class ProfileList(
    val id: String = "",
    val name: String = "",
    val createdFrom: String = "",
    val createdAt: String = "",
    val profiles: List<String> = listOf(),
    val follower: List<String> = listOf(),
    val settings: ListSettings = ListSettings()
)