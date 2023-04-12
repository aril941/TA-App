package com.example.ta1app.ui.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.ta1app.ui.info.InformationActivity
import com.example.ta1app.ui.kamus.KamusActivity
import com.example.ta1app.ui.settings.SettingsActivity
import com.example.ta1app.databinding.ActivityMainBinding
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.ta1app.R
import com.example.ta1app.ui.scan.ScanActivity
import com.example.ta1app.ui.settings.SettingMainViewModel
import com.example.ta1app.ui.settings.SettingPreferences
import com.example.ta1app.ui.settings.SettingViewModelFactory
import com.example.ta1app.ui.webview.WebviewActivity


private val Context.dataTheme: DataStore<Preferences> by preferencesDataStore(name = "theme")


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cvScan.setOnClickListener {
            val intent = Intent(this, ScanActivity::class.java)
            startActivity(intent)
        }
        binding.cvBuku.setOnClickListener {
            val intent = Intent(this, KamusActivity::class.java)
            startActivity(intent)
        }
        binding.cvInfo.setOnClickListener {
            val intent = Intent(this, InformationActivity::class.java)
            startActivity(intent)
        }
        binding.cvSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        setupSlider()
        setTheme()
    }

    private fun setupSlider(){
        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.slider1))
        imageList.add(SlideModel(R.drawable.slider2))
        imageList.add(SlideModel(R.drawable.slider3))
        imageList.add(SlideModel(R.drawable.slider4))
        binding.sliderImage.setImageList(imageList, ScaleTypes.FIT)
        binding.sliderImage.setItemClickListener(object : ItemClickListener {
            override fun onItemSelected(position: Int) {
                when (position) {
                    0 -> {
                        val intent = Intent(this@MainActivity, WebviewActivity::class.java)
                        intent.putExtra("url", getString(R.string.webLink1))
                        startActivity(intent)
                    }
                    1 -> {
                        val intent = Intent(this@MainActivity, WebviewActivity::class.java)
                        intent.putExtra("url", getString(R.string.webLink2))
                        startActivity(intent)
                    }
                    2 -> {
                        val intent = Intent(this@MainActivity, WebviewActivity::class.java)
                        intent.putExtra("url", getString(R.string.webLink3))
                        startActivity(intent)
                    }
                    3 -> {
                        val intent = Intent(this@MainActivity, WebviewActivity::class.java)
                        intent.putExtra("url", getString(R.string.webLink4))
                        startActivity(intent)
                    }
                }
            }
        })
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