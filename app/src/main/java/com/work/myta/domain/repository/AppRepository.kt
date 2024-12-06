package com.work.myta.domain.repository

import com.work.myta.domain.entity.AuthState

interface AppRepository {

    fun getAuthState(): AuthState
    fun performAuthResult()
}