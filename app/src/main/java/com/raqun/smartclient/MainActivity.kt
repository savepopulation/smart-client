package com.raqun.smartclient

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.raqun.smartclient.lib.SmartClient

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        writeHeaders()
    }

    private fun writeHeaders() {
        val textViewHeaders = findViewById<TextView>(R.id.textViewHeaders)
        val headers = SmartClient.generateHeaders()
        val sb = StringBuilder()
        headers.entries.forEach { entry ->
            sb.append("${entry.key} : ${entry.value}\n")
        }
        textViewHeaders.text = sb.toString()
    }
}
