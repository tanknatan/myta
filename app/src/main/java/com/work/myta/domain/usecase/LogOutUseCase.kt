package com.work.myta.domain.usecase

import com.work.myta.domain.repository.AppRepository

class LogOutUseCase(
    private val repository: AppRepository
) {

    fun invoke() = repository.logout()
}