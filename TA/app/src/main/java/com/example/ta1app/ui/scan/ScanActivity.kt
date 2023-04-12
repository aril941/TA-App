package com.example.ta1app.ui.scan

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.Matrix
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.view.View.*
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.ta1app.R
import com.example.ta1app.databinding.ActivityScanBinding
import java.io.IOException

class ScanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanBinding
    private lateinit var bitmap: Bitmap
    private lateinit var mClassifier: Classifier
    private val timer:Long = 3000
    private lateinit var handler: Handler

    private val mCameraRequestCode = 0
    private val mGalleryRequestCode = 2

    private val inputSize = 150
    private val mModelPath = "model.tflite"
    private val mLabelPath = "labels.txt"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mClassifier = Classifier(assets, mModelPath, mLabelPath, inputSize)

        binding.backBtn.setOnClickListener {
            Toast.makeText(this, "Button Back Clicked", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }
        startCamera()
        startGallery()
        imageView()
        detectButton()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == mCameraRequestCode) {
            //Considérons le cas de la caméra annulée
            if (resultCode == Activity.RESULT_OK && data != null) {
                bitmap = data.extras!!.get("data") as Bitmap
                bitmap = scaleImage(bitmap)
                binding.photoView.setImageBitmap(bitmap)
                if(bitmap == null){
                    binding.detect.visibility = INVISIBLE
                }else{
                    binding.detect.visibility = VISIBLE
                }


            } else {
                Toast.makeText(this, "Camera cancel..", Toast.LENGTH_LONG).show()
            }
        } else if (requestCode == mGalleryRequestCode) {
            if (data != null) {
                val uri = data.data

                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                bitmap = scaleImage(bitmap)
                binding.photoView.setImageBitmap(bitmap)
                if(bitmap == null){
                    binding.detect.visibility = INVISIBLE
                }else{
                    binding.detect.visibility = VISIBLE
                }
            }
        } else {
            Toast.makeText(this, "Unrecognized request code", Toast.LENGTH_LONG).show()

        }
    }

    private fun startGallery(){
        binding.gallery.setOnClickListener {
            val callGalleryIntent = Intent(Intent.ACTION_PICK).apply {
                type = "image/*"
            }
            startActivityForResult(callGalleryIntent, mGalleryRequestCode)
        }
    }
    private fun imageView() {
        binding.photoView.setImageResource(R.drawable.thumbnail)
    }

    private fun scaleImage(bitmap: Bitmap?): Bitmap {
        val orignalWidth = bitmap!!.width
        val originalHeight = bitmap.height
        val scaleWidth = inputSize.toFloat() / orignalWidth
        val scaleHeight = inputSize.toFloat() / originalHeight
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        return Bitmap.createBitmap(bitmap, 0, 0, orignalWidth, originalHeight, matrix, true)
    }
    private fun detectButton() {
        binding.detect.setOnClickListener {
            binding.loading.visibility = VISIBLE
            binding.textView2.visibility = INVISIBLE
            binding.card.visibility = INVISIBLE
            binding.card2.visibility = INVISIBLE
            handler = Handler()
            handler.postDelayed({
                binding.loading.visibility = INVISIBLE
                recognizeImage()
                binding.detect.visibility = INVISIBLE
            }, timer)
        }
    }

    private fun recognizeImage() {
        val results = mClassifier.recognizeImage(bitmap).firstOrNull()
        binding.resultText.text = results?.title
        binding.percentText.text = results?.confidence?.times(100)?.toInt().toString() + "%"
        binding.textView2.visibility = VISIBLE
        binding.card.visibility = VISIBLE
        binding.card2.visibility = VISIBLE
    }

    private fun startCamera() {
        binding.camera.setOnClickListener {
            val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(callCameraIntent, mCameraRequestCode)
        }
    }
}