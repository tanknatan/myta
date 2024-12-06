package com.work.myta.domain.usecase

import com.work.myta.domain.repository.AppRepository
import javax.inject.Inject

class CheckUserAuthUseCase @Inject constructor(
    private val authRepository: AppRepository
)
