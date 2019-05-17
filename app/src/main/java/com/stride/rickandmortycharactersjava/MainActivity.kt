package com.stride.rickandmortycharactersjava

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import android.content.Intent
import android.os.Bundle

import com.stride.rickandmortycharactersjava.data.api.RickAndMortyDataSource
import com.stride.rickandmortycharactersjava.feature.character_detail.CharacterDetailActivity
import com.stride.rickandmortycharactersjava.feature.character_list.RickAndMortyCharactersAdapter
import com.stride.rickandmortycharactersjava.model.RickAndMortyCharactersResponse

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RickAndMortyCharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.character_list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = RickAndMortyCharactersAdapter { item ->
            val intent = Intent(this@MainActivity, CharacterDetailActivity::class.java)
            intent.putExtra(CharacterDetailActivity.CHARACTER_ID, item.id)
            startActivity(intent)
        }
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        RickAndMortyDataSource.api.getCharacters().enqueue(object : Callback<RickAndMortyCharactersResponse> {
            override fun onResponse(call: Call<RickAndMortyCharactersResponse>, response: Response<RickAndMortyCharactersResponse>) {
                if (response.body() != null) {
                    adapter.setCharacters(response.body()!!.results)
                }
            }

            override fun onFailure(call: Call<RickAndMortyCharactersResponse>, t: Throwable) {}
        })
    }
}
