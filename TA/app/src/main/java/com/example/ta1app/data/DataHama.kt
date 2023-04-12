package com.example.ta1app.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataHama(
    val id: Int,
    val nama_hama: String,
    val nama_latin: String,
    val foto: Int,
    val deskripsi: String
) : Parcelable

