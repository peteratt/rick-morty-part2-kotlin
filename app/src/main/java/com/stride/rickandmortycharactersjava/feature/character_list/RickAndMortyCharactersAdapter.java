package com.stride.rickandmortycharactersjava.feature.character_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.stride.rickandmortycharactersjava.R;
import com.stride.rickandmortycharactersjava.model.RickAndMortyCharacter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RickAndMortyCharactersAdapter extends RecyclerView.Adapter {

    private final List<RickAndMortyCharacter> characters = new ArrayList<>();

    private final ItemListener<RickAndMortyCharacter> itemListener;

    public RickAndMortyCharactersAdapter(ItemListener<RickAndMortyCharacter> itemListener) {
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_character, parent, false);
        return new RickAndMortyCharacterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RickAndMortyCharacterViewHolder viewHolder = (RickAndMortyCharacterViewHolder) holder;
        final RickAndMortyCharacter character = characters.get(position);

        viewHolder.characterNameLabel.setText(character.name);

        ImageView characterImageView = viewHolder.characterImageView;
        Glide.with(characterImageView).load(character.image).into(characterImageView);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListener.onClick(character);
            }
        });
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public void setCharacters(List<RickAndMortyCharacter> newCharacters) {
        characters.clear();
        characters.addAll(newCharacters);
        notifyDataSetChanged();
    }
}
