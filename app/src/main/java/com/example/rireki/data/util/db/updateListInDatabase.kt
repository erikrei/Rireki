package com.example.rireki.data.util.db

import com.example.rireki.data.dataclass.ProfileList
import com.google.firebase.firestore.FirebaseFirestore

fun updateListInDatabase(
    db: FirebaseFirestore,
    list: ProfileList,
    onComplete: () -> Unit = { },
    onFailure: () -> Unit = { }
) {
    val docRef = db.collection("lists").document(list.id)

    docRef
        .set(list)
        .addOnCompleteListener {
            onComplete()
        }
        .addOnFailureListener {
            onFailure()
        }
}