package com.cortez.renato.pokeapi1

data class PokemonListResponse(
    val name: String,
    val url: String,
    val results: List<PokemonResponse>
)