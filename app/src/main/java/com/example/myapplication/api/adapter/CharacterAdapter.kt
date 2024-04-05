package com.example.myapplication.api.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.api.model.Character

class CharacterAdapter(private val onClick:(Character) -> Unit) :
    ListAdapter<Character, CharacterAdapter.CharacterViewHolder>(CharacterCallBack){

    class CharacterViewHolder(itemView: View, val onClick: (Character) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

        private val charImage: ImageView = itemView.findViewById(R.id.charImage)
        private val name: TextView = itemView.findViewById(R.id.name)
        private val species: TextView = itemView.findViewById(R.id.species)
        private val gender: TextView = itemView.findViewById(R.id.gender)

        private var currentCharacter: Character? = null

        init {
            itemView.setOnClickListener{
                currentCharacter?.let{onClick(it)}
            }
        }

        fun bind(character: Character){
            currentCharacter = character
            name.text = character.name
            species.text = character.species
            gender.text = character.gender

            Glide.with(itemView).load(character.image).centerCrop().into(charImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_character, parent, false)
        return  CharacterViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }
}
object CharacterCallBack : DiffUtil.ItemCallback<Character>(){

    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

}