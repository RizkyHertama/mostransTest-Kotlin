package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapplication.api.ApiClient
import com.example.myapplication.api.adapter.CharacterAdapter
import com.example.myapplication.api.model.Character
import com.example.myapplication.api.model.CharacterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterList : AppCompatActivity() {

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var call: Call<CharacterResponse>
    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)
        swipeRefresh = findViewById(R.id.refresh_layout)
        recyclerView = findViewById(R.id.recycler_view)

        characterAdapter = CharacterAdapter { character -> characterOnClick(character) }
        recyclerView.adapter = characterAdapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        swipeRefresh.setOnRefreshListener {
            getData()
        }
        getData()
    }

//    private fun characterOnClick(character: Character){
//        Toast.makeText(applicationContext, character.gender, Toast.LENGTH_SHORT).show()
//    }

    private fun characterOnClick(character: Character) {
        val intent = Intent(this, CharacterDetail::class.java)
        intent.putExtra("characterId", character.id) // Mengirim ID karakter ke halaman detail
        startActivity(intent)
    }

    private fun getData(){
        swipeRefresh.isRefreshing = true

        call = ApiClient.characterService.getAll()
        call.enqueue(object : Callback<CharacterResponse>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                swipeRefresh.isRefreshing = false
                if(response.isSuccessful){

                    characterAdapter.submitList(response.body()?.results)
                    characterAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                swipeRefresh.isRefreshing = false
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }
}