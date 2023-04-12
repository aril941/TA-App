package com.example.ta1app.ui.info

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.ta1app.databinding.ActivityInfoBinding
import com.example.ta1app.ui.settings.SettingMainViewModel
import com.example.ta1app.ui.settings.SettingPreferences
import com.example.ta1app.ui.settings.SettingViewModelFactory

private val Context.dataTheme: DataStore<Preferences> by preferencesDataStore(name = "theme")
class InformationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            Toast.makeText(this, "Button Back Clicked", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }

        setTheme()
    }

    private fun setTheme() {
        val pref = SettingPreferences.getInstance(dataTheme)
        val settingViewModel = ViewModelProvider(
            this,
            SettingViewModelFactory(pref)
        )[SettingMainViewModel::class.java]

        settingViewModel.getThemeSettings().observe(this)
        { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}
