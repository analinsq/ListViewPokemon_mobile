package com.example.listviewpoke

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PokemonDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)

        val pokemonName = intent.getStringExtra("pokemonName")
        val pokemonTypes = intent.getStringExtra("pokemonTypes")
        val pokemonImageResId = intent.getIntExtra("pokemonImage", 0)

        findViewById<TextView>(R.id.textViewName).text = pokemonName
        findViewById<TextView>(R.id.textViewTypes).text = "Types: $pokemonTypes"
        findViewById<ImageView>(R.id.imageViewPokemon).setImageResource(pokemonImageResId)
    }
}
