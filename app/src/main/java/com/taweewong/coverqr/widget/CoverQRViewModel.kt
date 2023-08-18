package com.taweewong.coverqr.widget

import androidx.lifecycle.ViewModel
import com.taweewong.coverqr.db.PreferencesManager

class CoverQRViewModel(
    private val preferencesManager: PreferencesManager
) : ViewModel() {
    private val mobilePrefKey = "mobile_number_key"

    var mobileNumber = preferencesManager.getData(mobilePrefKey, "")
    var amount = ""

    fun saveMobileNumber(mobileNumber: String) {
        preferencesManager.saveData(mobilePrefKey, mobileNumber)
    }

    fun reset() {
        mobileNumber = ""
        amount = ""
        preferencesManager.saveData(mobilePrefKey, "")
    }
}