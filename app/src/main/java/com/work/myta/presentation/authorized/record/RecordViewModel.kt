package com.work.myta.presentation.authorized.record

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.work.myta.data.dataStorage.DatabaseProvider
import com.work.myta.data.repository.AppRepositoryImpl
import com.work.myta.domain.entity.AnnaData
import com.work.myta.domain.entity.Appointment
import com.work.myta.domain.entity.AppotionData
import com.work.myta.domain.entity.EkaterinaData
import com.work.myta.domain.entity.ManikuryaData
import com.work.myta.domain.entity.MariaData
import com.work.myta.domain.entity.PedikuryaData
import com.work.myta.domain.entity.SalesData
import com.work.myta.domain.entity.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

class RecordViewModel(application: Application) : AndroidViewModel(application) {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> get() = _user

   private val _appointments = MutableStateFlow<List<Appointment>>(emptyList())
    val appointments: StateFlow<List<Appointment>> get() = _appointments

    var appointmentTime = MutableStateFlow(0)
    var appointmentDate = MutableStateFlow(0)
    var selectAppointment = MutableStateFlow<AppotionData>(AppotionData("","",0,0,"",0))



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
//    fun saveAppointmentData() {
//        viewModelScope.launch {
//            val appointment = selectAppointment
//            val user = user
//            val newAppointment  = Appointment()
//            userRepository.insert()
//
//        }
//    }

    fun updateAppointments() {
        viewModelScope.launch {
            _appointments.value = userRepository.getAppointmentsByUserId()
        }
    }


    fun foundArray(
        type: String?,
        categoryOrMaster: String?
    ): List<AppotionData> {
        return when (type!!) {
            "service" -> {
                when (categoryOrMaster) {
                    "Manikurya" -> ManikuryaData
                    "Pedikurya" -> PedikuryaData
                    "Sale" -> SalesData
                    else -> SalesData

                }
            }

            "Master" -> {
                when (categoryOrMaster) {
                    "Anna" -> AnnaData
                    "Ekaterina" -> EkaterinaData
                    "Maria" -> MariaData
                    else -> SalesData

                }

            }

            else -> {
                SalesData
            }
        }
    }

    fun getDaysInMonth(month: Int, year: Int): List<Int> {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, 1)

        // Получаем количество дней в текущем месяце
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        return (1..daysInMonth).toList()
    }


}