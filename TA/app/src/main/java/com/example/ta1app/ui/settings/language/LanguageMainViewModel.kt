package com.example.ta1app.ui.settings.language

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LanguageMainViewModel(private val pref: LanguagePreferences): ViewModel() {

    fun getLanguageSettings(): LiveData<Boolean> {
        return pref.getLanguageSettings().asLiveData()
    }

    fun saveLanguageSetting(language: Boolean) {
        viewModelScope.launch {
            pref.saveLanguageSetting(language)
        }
    }
}