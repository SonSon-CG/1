package com.example.rpa

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val DRAW_OVER_OTHER_APP_PERMISSION_REQUEST_CODE = 2084

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Nút để cấp quyền vẽ đè (floating overlay)
        findViewById<Button>(R.id.btnAskOverlayPermission).setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
                startActivityForResult(intent, DRAW_OVER_OTHER_APP_PERMISSION_REQUEST_CODE)
            } else {
                Toast.makeText(this, "Đã có quyền Overlay", Toast.LENGTH_SHORT).show()
                startFloatingService()
            }
        }
        
        // Nút để mở cài đặt Accessibility
        findViewById<Button>(R.id.btnAskAccessibility).setOnClickListener {
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            startActivity(intent)
        }
    }

    private fun startFloatingService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Settings.canDrawOverlays(this)) {
            startService(Intent(this@MainActivity, FloatingWebViewService::class.java))
        }
    }
}
