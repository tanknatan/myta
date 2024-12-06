package com.work.myta.di

import com.google.firebase.auth.FirebaseAuth
import com.work.myta.data.repository.AppRepositoryImpl
import ru.student.vknewsclient.presentation.MainViewModelFactory

class Component {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val authRepository = AppRepositoryImpl(firebaseAuth)

    fun getViewModelFactory(): MainViewModelFactory {
        return MainViewModelFactory(authRepository)
    }
}

