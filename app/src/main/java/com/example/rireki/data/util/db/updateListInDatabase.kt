package com.example.rireki.data.util.db

import android.util.Log
import com.example.rireki.data.dataclass.ProfileList
import com.google.firebase.firestore.FirebaseFirestore

fun updateListInDatabase(
    db: FirebaseFirestore,
    list: ProfileList,
    onComplete: () -> Unit = { },
    onFailure: () -> Unit = { }
) {
    Log.i("updateList", list.toString())

    val docRef = db.collection("lists").document(list.id)

    docRef
        .update("profiles", list.profiles)
        .addOnCompleteListener {
            onComplete()
        }
        .addOnFailureListener {
            onFailure()
        }
}