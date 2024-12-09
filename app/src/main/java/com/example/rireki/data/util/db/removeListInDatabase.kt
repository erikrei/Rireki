package com.example.rireki.data.util.db

import com.google.firebase.firestore.FirebaseFirestore

fun removeListInDatabase(
    db: FirebaseFirestore,
    listId: String,
    onComplete: () -> Unit = {},
    onFailure: () -> Unit = {}
) {


    val docRef = db.collection("lists").document(listId)

    docRef
        .delete()
        .addOnCompleteListener {
            onComplete()
        }
        .addOnFailureListener {
            onFailure()
        }
}