package com.example.fortune_horoscope.data.remote.firebase

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?

    suspend fun signIn(email: String, password: String): Result<FirebaseUser>

    suspend fun signUp(email: String, password: String): Result<FirebaseUser>

    fun signOut()
}