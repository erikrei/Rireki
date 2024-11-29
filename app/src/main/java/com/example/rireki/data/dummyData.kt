package com.example.rireki.data

import com.example.rireki.data.dataclass.Profile
import com.example.rireki.data.dataclass.ProfileList
import com.example.rireki.data.state.ListSettingsUiState
import java.time.LocalDate

val profiles: List<List<Profile>> = listOf(
    listOf(
        Profile(
            name = "Erik"
        ),
        Profile(
            name = "Julia"
        ),
        Profile(
            name = "Teresa"
        ),
        Profile(
            name = "Pia"
        ),
        Profile(
            name = "Leonie"
        ),
        Profile(
            name = "Gil"
        ),
        Profile(
            name = "Melanie"
        ),
        Profile(
            name = "Viktor"
        ),
        Profile(
            name = "Fabian"
        ),
        Profile(
            name = "Lara"
        ),
        Profile(
            name = "Fabiano"
        ),
        Profile(
            name = "Mario"
        ),
        Profile(
            name = "Anna"
        ),
        Profile(
            name = "Steffan"
        )
    ),
    listOf(
        Profile(
            name = "Rosa"
        ),
        Profile(
            name = "Georg"
        )
    )
)

val settings: List<ListSettingsUiState> = listOf(
    ListSettingsUiState(
        listId = "1",
        listName = "Jana's Bekanntschaft",
        admins = listOf("531413", "312513", "123125")
    ),
    ListSettingsUiState(
        listId = "2",
        listName = "Erik's Verwandschaft",
        admins = listOf("23151")
    )
)

val dummyLists: List<ProfileList> = listOf(
    ProfileList(id = "1", name = "Jana's Bekanntschaft", createdAt = LocalDate.of(2024, 11, 19), createdFrom = "Jana", profiles = profiles[0], settings = settings[0]),
    ProfileList(id = "2", name = "Erik's Verwandschaft", createdAt = LocalDate.of(2024, 7, 8), createdFrom = "Erik", profiles = profiles[1], settings = settings[1]),
)