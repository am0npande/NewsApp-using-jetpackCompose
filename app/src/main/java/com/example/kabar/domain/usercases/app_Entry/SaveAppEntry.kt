package com.example.kabar.domain.usercases.app_Entry

import com.example.kabar.domain.manager.LocalUserManager

class SaveAppEntry (private val localUserManager: LocalUserManager){

    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}