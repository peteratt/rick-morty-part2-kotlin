package com.stride.rickandmortycharactersjava.data.api

import com.stride.rickandmortycharactersjava.model.RickAndMortyCharacter
import com.stride.rickandmortycharactersjava.model.RickAndMortyCharactersResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {
    @GET("character")
    fun getCharacters(): Call<RickAndMortyCharactersResponse>

    @GET("character/{id}")
    fun getCharacter(@Path("id") id: Long): Call<RickAndMortyCharacter>
}

object RickAndMortyDataSource {
    val api: RickAndMortyApi

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        api = retrofit.create(RickAndMortyApi::class.java)
    }
}
