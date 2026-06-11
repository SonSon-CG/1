package com.example.rpa

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

class FloatingWebViewService : Service() {
    private lateinit var windowManager: WindowManager
    private lateinit var floatingView: View

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        
        // Inject Floating UI
        floatingView = LayoutInflater.from(this).inflate(R.layout.layout_floating_widget, null)

        val layoutFlag: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            WindowManager.LayoutParams.TYPE_PHONE
        }

        // Thiết lập WindowManager cho phép vẽ đè, nền trong suốt
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
            layoutFlag,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            PixelFormat.TRANSLUCENT
        )

        params.gravity = Gravity.TOP or Gravity.LEFT

        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        windowManager.addView(floatingView, params)

        val webView = floatingView.findViewById<WebView>(R.id.webView)
        
        // Bật JS
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        // Trong suốt
        webView.setBackgroundColor(0x00000000)
        
        // Thêm Bridge kết nối JS và Kotlin
        webView.addJavascriptInterface(WebAppInterface(this, null), "Android")

        // Load source từ file tĩnh (React/Vite build dist) đã đưa vào assets
        webView.loadUrl("file:///android_asset/dist/index.html")
        // Hoặc load qua mạng nội bộ lúc dev:
        // webView.loadUrl("http://192.168.x.x:3000/")
        
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::floatingView.isInitialized) {
            windowManager.removeView(floatingView)
        }
    }
}
