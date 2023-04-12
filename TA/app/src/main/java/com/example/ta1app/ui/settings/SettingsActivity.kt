package com.example.ta1app.ui.settings

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.ta1app.R
import com.example.ta1app.databinding.ActivitySettingsBinding
import java.util.*

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private val Context.dataTheme: DataStore<Preferences> by preferencesDataStore(name = "theme")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            Toast.makeText(this, "Button Back Clicked", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }



        setTheme()
//        setLanguage()

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
                binding.switchTema.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.switchTema.isChecked = false
            }
        }
        binding.switchTema.setOnCheckedChangeListener { _, isChecked: Boolean ->
            settingViewModel.saveThemeSetting(isChecked)
        }
    }
//    private fun setLanguage(){
//        binding.bahasa.setOnClickListener {
//            val bsd = BottomSheetDialog()
//            bsd.show(supportFragmentManager, bsd.tag)
//            val indonesia = findViewById<RadioButton>(R.id.ind_button)
//            val english = findViewById<RadioButton>(R.id.en_button)
//
//            if (indonesia.isChecked) {
//                setLocale("in")
//            } else if (english.isChecked) {
//                setLocale("en")
//            }
//        }
//    }
//
//    private fun setLocale(locale: String){
//        val locale = Locale(locale)
//        Locale.setDefault(locale)
//        val config = Configuration()
//        config.locale = locale
//        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
//        val intent = Intent(this, SettingsActivity::class.java)
//        startActivity(intent)
//    }
}