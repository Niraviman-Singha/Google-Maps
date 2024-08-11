package com.example.googlemaps

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class FireStoreViewModel:ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()
    private val userCollection = firestore.collection("users")

    fun saveUser(userId:String, displayName:String, email:String, location:String){
        val user = hashMapOf(
            "displayName" to displayName,
            "email" to email,
            "location" to location
        )
        userCollection.document(userId).set(user)
            .addOnSuccessListener {  }
            .addOnFailureListener { e-> }

    }

    fun getAllUser(callback:(List<User>) -> Unit){
        userCollection.get()
            .addOnSuccessListener { result->
                val userList = mutableListOf<User>()
                for(document in result ){
                    val userId = document.id
                }
            }
    }

}