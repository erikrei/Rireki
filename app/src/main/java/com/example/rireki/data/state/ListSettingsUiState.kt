package com.example.rireki.data.state

data class ListSettingsUiState(
    val newName: String = "",
    val privacy: String = "Privat",
    var privacyOptions: List<String> = listOf(
        "Privat", "Öffentlich", "Geschlossen", "Bestätigung"
    ),
    var expandedDropdown: Boolean = false,
    var expandedListDelete: Boolean = false,
    val expandedAdminAdd: Boolean = false,
    val expandedAdminRemove: Boolean = false,
    val dialogAdmin: String = ""
)