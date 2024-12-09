package com.example.rireki.data.state

data class ListSettingsUiState(
    val id: String = "",
    val newName: String = "",
    val admins: List<String> = listOf(),
    val privacy: String = "Privat",
    var privacyOptions: List<String> = listOf(
        "Privat", "Öffentlich", "Geschlossen", "Bestätigung"
    ),
    var expandedDropdown: Boolean = false,
    var expandedListDelete: Boolean = false,
)