package com.example.rireki.data.util.db

import com.example.rireki.data.dataclass.ProfileList
import com.google.firebase.firestore.FirebaseFirestore

fun createListInDatabase(
    db: FirebaseFirestore,
    list: ProfileList,
    changeIdOfCreatedList: (String) -> Unit,
    onComplete: () -> Unit = {},
    onFailure: () -> Unit = {},
) {
    val docRef = db.collection("lists").document()

    docRef
        .set(
            getDBList(list = list, id = docRef.id)
        )
        .addOnCompleteListener {
            onComplete()
            changeIdOfCreatedList(docRef.id)
        }
        .addOnFailureListener {
            onFailure()
        }
}