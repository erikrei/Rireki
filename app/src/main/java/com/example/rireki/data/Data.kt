package com.example.rireki.data

import com.example.rireki.data.objects.ProfileList
import java.time.LocalDate

val dummyLists: List<ProfileList> = listOf<ProfileList>(
    ProfileList("Jana's Bekanntschaft", createdAt = LocalDate.of(2024, 11, 19), createdFrom = "Jana Theisejans"),
    ProfileList("Erik's Verwandschaft", createdAt = LocalDate.of(2024, 7, 8), createdFrom = "Erik Reisswig"),
)