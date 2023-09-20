package com.capoeira.bimbatrainer.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.webkit.WebView
import androidx.annotation.RequiresApi
import com.capoeira.bimbatrainer.R

class InstructionsActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructions)
        initializeText()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun initializeText() {
        var instructionsWebView = findViewById<WebView>(R.id.instructionsWebView)
        var filePath = getString(R.string.text_basepath) + "intro.txt"
        var instructionHtml = convertFileContentToHtml(filePath)
        instructionsWebView.loadData(instructionHtml, "text/html", "base64")
    }

    private fun convertFileContentToHtml(filePath: String): String {
        var bufferedReader = assets.open(filePath).bufferedReader()
        var instructions = bufferedReader.use {
            it.readText()
        }
        var instructionHtml = Base64.encodeToString(instructions.toByteArray(), Base64.NO_PADDING)
        return instructionHtml
    }
}