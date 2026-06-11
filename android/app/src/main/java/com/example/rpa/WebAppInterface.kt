package com.example.rpa

import android.content.Context
import android.webkit.JavascriptInterface
import android.widget.Toast

class WebAppInterface(private val mContext: Context, private val service: RpaService?) {

    @JavascriptInterface
    fun startRecording(macroId: String) {
        // Gọi xuống service để ghi hình
        Toast.makeText(mContext, "Bắt đầu REC: $macroId", Toast.LENGTH_SHORT).show()
    }

    @JavascriptInterface
    fun stopRecording() {
        Toast.makeText(mContext, "Dừng REC", Toast.LENGTH_SHORT).show()
    }

    @JavascriptInterface
    fun startPlaying(macroId: String, rpaEnabled: Boolean, formDataStr: String) {
        Toast.makeText(mContext, "Chạy RPA: $macroId", Toast.LENGTH_SHORT).show()
        // Phân tích formDataStr (JSON) và gửi lệnh thực thi auto-click qua AccessibilityService
        service?.startRpaTask(macroId, formDataStr)
    }

    @JavascriptInterface
    fun stopPlaying() {
        Toast.makeText(mContext, "Dừng RPA", Toast.LENGTH_SHORT).show()
        service?.stopRpaTask()
    }

    @JavascriptInterface
    fun pauseAction() {
        Toast.makeText(mContext, "Tạm dừng", Toast.LENGTH_SHORT).show()
        service?.pauseRpaTask()
    }

    @JavascriptInterface
    fun saveConfig(dataStr: String) {
        // Lưu config vào SharedPreferences hoặc File
        Toast.makeText(mContext, "Đã lưu config Android", Toast.LENGTH_SHORT).show()
    }
}
