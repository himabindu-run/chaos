package com.example.chaos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AboutXmlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_xml)
        
        supportActionBar?.title = "About Me (XML Layout)"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}