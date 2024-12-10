package com.work.myta.presentation.authorized.recording

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.work.myta.domain.entity.AnnaData
import com.work.myta.domain.entity.AppotionData
import com.work.myta.domain.entity.EkaterinaData
import com.work.myta.domain.entity.ManikuryaData
import com.work.myta.domain.entity.MariaData
import com.work.myta.domain.entity.PedikuryaData
import com.work.myta.domain.entity.SalesData

class RecordingScreenViewModel(application: Application) : AndroidViewModel(application) {

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

}
