package com.example.rireki.data.state

data class ListSettingsUiState(
    val listId: String = "",
    val listName: String = "",
    val admins: List<String> = listOf(),
    val listPrivacy: String = "Privat",
    var listPrivacyOptions: List<String> = listOf(
        "Privat", "Öffentlich", "Geschlossen", "Bestätigung"
    ),
    var expandedDropdown: Boolean = false,
    var expandedListDelete: Boolean = false,
)
