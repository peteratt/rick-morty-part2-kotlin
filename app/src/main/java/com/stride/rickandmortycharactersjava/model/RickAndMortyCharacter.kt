package com.stride.rickandmortycharactersjava.model

data class RickAndMortyCharacter(val id: Long, val name: String, val image: String)

data class RickAndMortyCharactersResponse(val results: List<RickAndMortyCharacter>)
