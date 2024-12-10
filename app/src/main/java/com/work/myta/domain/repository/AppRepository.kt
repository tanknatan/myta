package com.work.myta.domain.repository

import com.work.myta.domain.entity.User

interface AppRepository {

    fun getAuthState(): Int

    fun saveAuthState( id : Int)

    fun logout()

    fun updateState()

}