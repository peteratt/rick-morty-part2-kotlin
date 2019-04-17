package com.stride.rickandmortycharactersjava.data.api;

import com.stride.rickandmortycharactersjava.model.RickAndMortyCharacter;
import com.stride.rickandmortycharactersjava.model.RickAndMortyCharactersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RickAndMortyApi {
    @GET("character")
    public Call<RickAndMortyCharactersResponse> getCharacters();

    @GET("character/{id}")
    public Call<RickAndMortyCharacter> getCharacter(@Path("id") long id);
}
