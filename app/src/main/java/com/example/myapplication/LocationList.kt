package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapplication.api.ApiClient
import com.example.myapplication.api.adapter.LocationAdapter
import com.example.myapplication.api.model.Location
import com.example.myapplication.api.model.LocationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LocationList : AppCompatActivity() {

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var call: Call<LocationResponse>
    private lateinit var locationAdapter: LocationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_list)
        swipeRefresh = findViewById(R.id.refresh_layout)
        recyclerView = findViewById(R.id.recycler_view)

        locationAdapter = LocationAdapter { location -> locationOnClick(location) }
        recyclerView.adapter = locationAdapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        swipeRefresh.setOnRefreshListener {
            getData()
        }
        getData()
    }

    private fun locationOnClick(location: Location) {
        Toast.makeText(applicationContext, location.name, Toast.LENGTH_SHORT).show()
    }

    private fun getData(){
        swipeRefresh.isRefreshing = true

        call = ApiClient.locationService.getAll()
        call.enqueue(object : Callback<LocationResponse> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<LocationResponse>,
                response: Response<LocationResponse>
            ) {
                swipeRefresh.isRefreshing = false
                if(response.isSuccessful){
                    locationAdapter.submitList(response.body()?.results)
                    locationAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<LocationResponse>, t: Throwable) {
                swipeRefresh.isRefreshing = false
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

}