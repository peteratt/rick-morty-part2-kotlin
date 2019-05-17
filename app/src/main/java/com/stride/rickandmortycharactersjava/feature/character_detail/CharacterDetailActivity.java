package com.stride.rickandmortycharactersjava.feature.character_detail;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.stride.rickandmortycharactersjava.R;
import com.stride.rickandmortycharactersjava.data.api.RickAndMortyApi;
import com.stride.rickandmortycharactersjava.data.api.RickAndMortyDataSource;
import com.stride.rickandmortycharactersjava.feature.character_list.RickAndMortyCharacterViewHolder;
import com.stride.rickandmortycharactersjava.model.RickAndMortyCharacter;

import org.jetbrains.annotations.NotNull;

public class CharacterDetailActivity extends AppCompatActivity {

    public static final String CHARACTER_ID = "character_id";

    private final RickAndMortyApi api = RickAndMortyDataSource.INSTANCE.getApi();

    private ImageView image;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);

        image = findViewById(R.id.character_image);
        name = findViewById(R.id.character_name);

        long id = getIntent().getLongExtra(CHARACTER_ID, -1);

        if (id != -1) {
            api.getCharacter(id).enqueue(new Callback<RickAndMortyCharacter>() {
                @Override
                public void onResponse(@NotNull Call<RickAndMortyCharacter> call, @NotNull Response<RickAndMortyCharacter> response) {
                    if (response.body() != null) {
                        name.setText(response.body().getName());
                        Glide.with(image).load(response.body().getImage()).into(image);
                    }
                }

                @Override
                public void onFailure(@NotNull Call<RickAndMortyCharacter> call, @NotNull Throwable t) {}
            });
        } else {
            finish();
        }
    }
}
