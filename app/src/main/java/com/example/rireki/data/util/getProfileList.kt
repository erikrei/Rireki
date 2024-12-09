package com.example.rireki.data.util

import com.example.rireki.data.dataclass.ListSettings
import com.example.rireki.data.dataclass.ProfileList
import java.time.LocalDate

fun getProfileList(listName: String, userName: String, userId: String): ProfileList {
    return ProfileList(
        name = listName,
        createdFrom = userName,
        createdAt = getReadableStringOfLocalDate(LocalDate.now()),
        follower = listOf(userId),
        settings = ListSettings(
            admins = listOf(userId)
        )
    )
}