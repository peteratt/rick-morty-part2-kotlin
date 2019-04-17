package com.stride.rickandmortycharactersjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import android.content.Intent;
import android.os.Bundle;

import com.stride.rickandmortycharactersjava.data.api.RickAndMortyApi;
import com.stride.rickandmortycharactersjava.feature.character_detail.CharacterDetailActivity;
import com.stride.rickandmortycharactersjava.feature.character_list.ItemListener;
import com.stride.rickandmortycharactersjava.feature.character_list.RickAndMortyCharactersAdapter;
import com.stride.rickandmortycharactersjava.model.RickAndMortyCharacter;
import com.stride.rickandmortycharactersjava.model.RickAndMortyCharactersResponse;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RickAndMortyApi api;
    private RecyclerView recyclerView;
    private RickAndMortyCharactersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.character_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RickAndMortyCharactersAdapter(new ItemListener<RickAndMortyCharacter>() {
            @Override
            public void onClick(RickAndMortyCharacter item) {
                Intent intent = new Intent(MainActivity.this, CharacterDetailActivity.class);
                intent.putExtra(CharacterDetailActivity.CHARACTER_ID, item.id);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
        api = retrofit.create(RickAndMortyApi.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        api.getCharacters().enqueue(new Callback<RickAndMortyCharactersResponse>() {
            @Override
            public void onResponse(Call<RickAndMortyCharactersResponse> call, Response<RickAndMortyCharactersResponse> response) {
                if (response.body() != null) {
                    adapter.setCharacters(response.body().results);
                }
            }

            @Override
            public void onFailure(Call<RickAndMortyCharactersResponse> call, Throwable t) {}
        });
    }
}
