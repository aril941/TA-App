package com.example.ta1app.ui.settings

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class SettingMainViewModel (private val pref: SettingPreferences) : ViewModel() {

    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            pref.setThemeSetting(isDarkModeActive)
        }
    }
}
