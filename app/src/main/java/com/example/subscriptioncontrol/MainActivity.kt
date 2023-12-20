package com.example.subscriptioncontrol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addSubcription: Button = findViewById(R.id.addSubcription)
        val subscriptionList = findViewById<ListView>(R.id.subscriptionList)
        addSubcription.setOnClickListener{
            Toast.makeText(this,"test press button", Toast.LENGTH_SHORT).show()
        }
    }
}