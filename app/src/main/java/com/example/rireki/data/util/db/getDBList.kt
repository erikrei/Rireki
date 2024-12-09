package com.example.rireki.data.util.db

import com.example.rireki.data.dataclass.ProfileList

fun getDBList(list: ProfileList, id: String): ProfileList {
    return list.copy(
        id = id
    )
}