package com.work.myta.domain.usecase

import com.work.myta.domain.repository.AppRepository

class SaveAuthStateUseCase(
    private val repository: AppRepository
) {

    fun invoke(id: Int) = repository.saveAuthState(id)
}