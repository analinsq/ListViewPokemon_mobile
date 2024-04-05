package com.example.listviewpoke

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val pokemonList = listOf(
        Pokemon("Bulbasaur", listOf("Grama", "Venenoso"), R.drawable.bulbasaur),
        Pokemon("Charmander", listOf("Fogo"), R.drawable.charmander),
        Pokemon("Squirtle", listOf("Água"), R.drawable.squirtle)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listView)
        val adapter = PokemonListAdapter(this, pokemonList)
        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedPokemon = pokemonList[position]
            val intent = Intent(this, PokemonDetailsActivity::class.java).apply {
                putExtra("pokemonName", selectedPokemon.name)
                putExtra("pokemonTypes", selectedPokemon.types.joinToString(", "))
                putExtra("pokemonImage", selectedPokemon.imageResId)
            }
            startActivity(intent)
        }
    }

    inner class PokemonListAdapter(context: Context, private val pokemonList: List<Pokemon>) :
        ArrayAdapter<Pokemon>(context, 0, pokemonList) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var itemView = convertView
            if (itemView == null) {
                itemView = LayoutInflater.from(context).inflate(R.layout.pokemon_list_item, parent, false)
            }

            val currentItem = pokemonList[position]

            val imageViewPokemon = itemView!!.findViewById<ImageView>(R.id.imageViewPokemon)
            imageViewPokemon.setImageResource(currentItem.imageResId)

            val textViewName = itemView.findViewById<TextView>(R.id.textViewName)
            textViewName.text = currentItem.name

            return itemView
        }
    }
}

data class Pokemon(val name: String, val types: List<String>, val imageResId: Int)