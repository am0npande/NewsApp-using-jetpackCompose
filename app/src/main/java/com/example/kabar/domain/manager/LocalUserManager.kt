package com.example.kabar.domain.manager

import kotlinx.coroutines.flow.Flow


//in this interface we wanna save app entry when user clicks on GET STARTED in onboarding page

interface LocalUserManager {

    suspend fun saveAppEntry()

    fun readAppEntry(): Flow<Boolean>
}
