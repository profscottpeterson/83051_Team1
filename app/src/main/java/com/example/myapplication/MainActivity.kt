package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find my button by its ID and set it to machineButton
        val machineButton = findViewById<Button>(R.id.btnMessage)

        machineButton.setOnClickListener {
            // Display toast class (widget)
            Toast.makeText(this,"Hello Team Software Dev", Toast.LENGTH_LONG).show();
        }
    }
}
