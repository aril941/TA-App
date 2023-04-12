package com.example.ta1app.data


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataTanaman(
    val id: Int,
    val nama_tanaman: String,
    val nama_latin: String,
    val foto: Int,
    val deskripsi: String
) : Parcelable
