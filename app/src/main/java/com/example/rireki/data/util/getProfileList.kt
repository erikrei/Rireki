package com.example.rireki.data.util

import com.example.rireki.data.dataclass.ProfileList
import java.time.LocalDate

fun getProfileList(listName: String): ProfileList {
    return ProfileList(
        name = listName,
        createdFrom = "Test User",
        createdAt = LocalDate.now()
    )
}