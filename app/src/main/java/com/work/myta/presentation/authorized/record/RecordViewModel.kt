package com.work.myta.presentation.authorized.record

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.work.myta.data.dataStorage.DatabaseProvider
import com.work.myta.data.repository.AppRepositoryImpl
import com.work.myta.domain.entity.Appointment
import com.work.myta.domain.entity.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecordViewModel(application: Application) : AndroidViewModel(application) {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> get() = _user

   private val _appointments = MutableStateFlow<List<Appointment>>(emptyList())
    val appointments: StateFlow<List<Appointment>> get() = _appointments

    val userRepository: AppRepositoryImpl

    init {
        // Получаем доступ к репозиторию
        val userDao = DatabaseProvider.getDatabase(application).userDao()
        userRepository = AppRepositoryImpl
        viewModelScope.launch {
            _user.value = userRepository.getUserById()
            _appointments.value = userRepository.getAppointmentsByUserId()
        }
    }

    fun updateAppointments() {
        viewModelScope.launch {
            _appointments.value = userRepository.getAppointmentsByUserId()
        }
    }



}