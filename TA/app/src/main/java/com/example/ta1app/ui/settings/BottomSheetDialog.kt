package com.example.ta1app.ui.settings

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.app.ActivityCompat.recreate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.ta1app.R
import com.example.ta1app.databinding.BottomsheetBinding
import com.example.ta1app.ui.settings.language.LanguageMainViewModel
import com.example.ta1app.ui.settings.language.LanguagePreferences
import com.example.ta1app.ui.settings.language.LanguageViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*


class BottomSheetDialog : BottomSheetDialogFragment() {
    private val Context.setLanguage: DataStore<Preferences> by preferencesDataStore(name = "language")
    private lateinit var indButton: RadioButton
    private lateinit var enButton: RadioButton




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.bottomsheet, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = BottomsheetBinding.bind(view)

        indButton = binding.indButton
        enButton = binding.enButton



        binding.indButton.setOnClickListener {
//            settingViewModel.saveLanguageSetting("in")
            setLocale("in")
            dismiss()
        }

        binding.enButton.setOnClickListener {
//            settingViewModel.saveLanguageSetting("en")
            setLocale("en")
            dismiss()
        }
    }

//    private fun setLanguage(){
//        val pref = LanguagePreferences.getInstance(setLanguage)
//        val languageViewModel = ViewModelProvider(
//            this,
//            LanguageViewModelFactory(pref)
//        )[LanguageMainViewModel::class.java]
//    }

    private fun setLocale(language: String){
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        requireActivity().baseContext.resources.updateConfiguration(
            config,
            requireActivity().baseContext.resources.displayMetrics
        )
        recreate(requireActivity())
    }



    companion object {
        const val TAG = "ModalBottomSheet"
    }
}