package com.stride.rickandmortycharactersjava.feature.character_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.stride.rickandmortycharactersjava.R
import com.stride.rickandmortycharactersjava.model.RickAndMortyCharacter

import java.util.ArrayList
import androidx.recyclerview.widget.RecyclerView

class RickAndMortyCharactersAdapter(private val itemListener: (RickAndMortyCharacter) -> Unit) : RecyclerView.Adapter<RickAndMortyCharacterViewHolder>() {

    private val characters = mutableListOf<RickAndMortyCharacter>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickAndMortyCharacterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_character, parent, false)
        return RickAndMortyCharacterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RickAndMortyCharacterViewHolder, position: Int) {
        val viewHolder = holder as RickAndMortyCharacterViewHolder
        val character = characters[position]

        viewHolder.characterNameLabel.text = character.name

        val characterImageView = viewHolder.characterImageView
        Glide.with(characterImageView).load(character.image).into(characterImageView)

        viewHolder.itemView.setOnClickListener { itemListener(character) }
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    fun setCharacters(newCharacters: List<RickAndMortyCharacter>) {
        characters.clear()
        characters.addAll(newCharacters)
        notifyDataSetChanged()
    }
}

class RickAndMortyCharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val characterNameLabel: TextView = itemView.findViewById(R.id.character_name)
    val characterImageView: ImageView = itemView.findViewById(R.id.character_image)
}
