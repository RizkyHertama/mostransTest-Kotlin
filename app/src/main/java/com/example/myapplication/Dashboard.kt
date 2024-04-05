package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView


class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Temukan CardView characterList
        val characterListCard = findViewById<CardView>(R.id.characterCard)

        // Tambahkan OnClickListener
        characterListCard.setOnClickListener {
            // Intent untuk mengarahkan ke CharacterList.kt
            val intent = Intent(this, CharacterList::class.java)
            startActivity(intent)
        }

        val locationListCard = findViewById<CardView>(R.id.locationCard)

        locationListCard.setOnClickListener{
            val intent = Intent(this, LocationList::class.java)
            startActivity(intent)
        }

    }

}
