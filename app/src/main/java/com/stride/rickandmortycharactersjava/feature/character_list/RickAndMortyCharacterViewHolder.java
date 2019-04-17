package com.stride.rickandmortycharactersjava.feature.character_list;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.stride.rickandmortycharactersjava.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RickAndMortyCharacterViewHolder extends RecyclerView.ViewHolder {

    public final TextView characterNameLabel;
    public final ImageView characterImageView;

    public RickAndMortyCharacterViewHolder(@NonNull View itemView) {
        super(itemView);

        characterNameLabel = itemView.findViewById(R.id.character_name);
        characterImageView = itemView.findViewById(R.id.character_image);
    }
}
