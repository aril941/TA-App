package com.example.ta1app.ui.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ta1app.databinding.ActivityWebviewBinding

class WebviewActivity : AppCompatActivity() {
    private lateinit var  webBinding : ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        webBinding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(webBinding.root)

        val url = intent.getStringExtra("url")
        webBinding.webView.loadUrl(url.toString())


    }
}