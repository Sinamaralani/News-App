package com.example.newsapp.domain.usecases.app_entry

import com.example.newsapp.domain.manger.LocalUserManger
import javax.inject.Inject

class SaveAppEntry (private val localUserManger: LocalUserManger) {

    suspend operator fun invoke(){
        localUserManger.saveAppEntry()
    }
}