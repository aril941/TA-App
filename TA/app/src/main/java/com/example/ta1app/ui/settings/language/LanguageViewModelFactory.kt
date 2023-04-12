package com.example.ta1app.ui.settings.language

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LanguageViewModelFactory(private val pref: LanguagePreferences):ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LanguageMainViewModel::class.java)) {
            return LanguageMainViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}