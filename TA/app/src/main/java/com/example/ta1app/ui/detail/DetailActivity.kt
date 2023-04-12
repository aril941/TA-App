package com.example.ta1app.ui.detail

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.ta1app.data.DataHama
import com.example.ta1app.data.DataTanaman
import com.example.ta1app.databinding.ActivityDetailBinding
import com.example.ta1app.ui.settings.SettingMainViewModel
import com.example.ta1app.ui.settings.SettingPreferences
import com.example.ta1app.ui.settings.SettingViewModelFactory

private val Context.dataTheme: DataStore<Preferences> by preferencesDataStore(name = "theme")
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            Toast.makeText(this, "Button Back Clicked", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }

        dataTanaman()
        dataHama()
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
    private fun dataTanaman(){
        val dataTanaman = intent.getParcelableExtra<DataTanaman>("DATA_TANAMAN")
        dataTanaman?.let {
            binding.txtIndonesia.text = it.nama_tanaman
            binding.txtLatin.text = it.nama_latin
            binding.txtDescription.text = it.deskripsi
            binding.imageDetail.setImageResource(it.foto)
        }
    }
    private fun dataHama(){
        val dataHama = intent.getParcelableExtra<DataHama>("DATA_HAMA")
        dataHama?.let {
            binding.txtIndonesia.text = it.nama_hama
            binding.txtLatin.text = it.nama_latin
            binding.txtDescription.text = it.deskripsi
            binding.imageDetail.setImageResource(it.foto)
        }
    }
}