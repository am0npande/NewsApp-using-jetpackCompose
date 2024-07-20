package com.example.kabar.domain.usercases.app_Entry

import com.example.kabar.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry (private val localUserManager: LocalUserManager){
    operator fun invoke(): Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}