package com.example.ta1app.ui.kamus

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.ta1app.databinding.ActivityKamusBinding
import com.example.ta1app.ui.kamus.fragment.FragmentAdapter
import com.example.ta1app.ui.settings.SettingMainViewModel
import com.example.ta1app.ui.settings.SettingPreferences
import com.example.ta1app.ui.settings.SettingViewModelFactory
import com.google.android.material.tabs.TabLayout

private val Context.dataTheme: DataStore<Preferences> by preferencesDataStore(name = "theme")
class KamusActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKamusBinding
    private lateinit var adapter: FragmentAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKamusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            Toast.makeText(this, "Button Back Clicked", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }

        setViewPager()
        setTheme()
    }

    private fun setViewPager(){
        adapter = FragmentAdapter(supportFragmentManager, lifecycle)
        binding.viewPager2.adapter = adapter
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab != null) {
                    binding.viewPager2.currentItem = tab.position
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
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