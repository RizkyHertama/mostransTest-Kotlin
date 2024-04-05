package com.example.myapplication.api.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.api.model.Location

class LocationAdapter(private val onClick:(Location) -> Unit) :
    ListAdapter<Location, LocationAdapter.LocationViewHolder>(LocationCallBack){

    class LocationViewHolder(itemView: View, val onClick: (Location) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(R.id.name)
        private val type: TextView = itemView.findViewById(R.id.type)
        private val dimension: TextView = itemView.findViewById(R.id.dimension)

        private var currentLocation: Location? = null

        init {
            itemView.setOnClickListener{
                currentLocation?.let{onClick(it)}
            }
        }

        fun bind(location: Location){
            currentLocation = location
            name.text = location.name
            type.text = location.type
            dimension.text = location.dimension
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_location, parent, false)
        return  LocationViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location = getItem(position)
        holder.bind(location)
    }
}

object LocationCallBack : DiffUtil.ItemCallback<Location>(){

    override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
        return oldItem.id == newItem.id
    }

}