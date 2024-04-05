package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myapplication.api.ApiClient
import com.example.myapplication.api.model.CharacterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        // Mendapatkan ID karakter dari Intent
        val characterId = intent.getIntExtra("characterId", -1).toInt()

//        val characterId = 1
        Log.d("CharacterDetail", "Received characterId from Intent: $characterId")

        // Panggil metode untuk mendapatkan detail karakter berdasarkan ID
        getCharacterDetail(characterId)
    }

    private fun getCharacterDetail(characterId: Int) {
        // Panggil service untuk mendapatkan detail karakter
//        ApiClient.characterService.getById(characterId).enqueue(object : Callback<CharacterResponse> {
            ApiClient.characterService.getAll().enqueue(object : Callback<CharacterResponse> {
            override fun onResponse(call: Call<CharacterResponse>, response: Response<CharacterResponse>) {
                if (response.isSuccessful) {
                    val characterResponse = response.body()
                    Log.e("Character Respo", "$characterResponse")
                    if (characterResponse != null && characterResponse.results != null) {
                        val character = characterResponse.results.firstOrNull()
                        if (character != null) {
                            // Tampilkan detail karakter di Activity ini, misalnya dengan mengubah teks pada TextView
                            Glide.with(this@CharacterDetail)
                                .load(character.image)
                                .into(findViewById<ImageView>(R.id.image_character))
                            findViewById<TextView>(R.id.text_name).text = character.name
                            findViewById<TextView>(R.id.text_status).text = character.status
                            findViewById<TextView>(R.id.text_species).text = character.species
                            findViewById<TextView>(R.id.text_gender).text = character.gender

                            findViewById<TextView>(R.id.text_origin).text = character.origin.name


                            findViewById<TextView>(R.id.text_location).text = character.location.name
                        } else {

                            Log.e("CharacterDetail", "No character found for ID: $characterId")
                        }
                    } else {
                        Log.e("CharacterDetail", "Character response or results is null")
                    }
                } else {
                    Log.e("CharacterDetail", "Failed to get character detail: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                // Menampilkan pesan error jika gagal mendapatkan detail karakter
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
                Log.e("CharacterDetail", "Error getting character detail: ${t.localizedMessage}")
            }
        })
    }
}
