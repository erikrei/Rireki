package com.example.rireki.data.util

import com.example.rireki.data.dataclass.ProfileList

fun getProfileListFromId(lists: List<ProfileList>, listId: String): ProfileList? {
    return lists.find { it.id == listId }
}