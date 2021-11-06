package com.shrek.pokemonlibrary.network.data.response

import com.shrek.pokemonlibrary.client.PokemonClient

data class GetPokemonResponse (
    var id : Int,
    var name : String,
    var species : Species?,
    var sprites: Sprites?,
)

fun GetPokemonResponse.isValid() : Boolean {
    return !species?.name.isNullOrBlank() && !sprites?.frontDefault.isNullOrBlank()
}

fun GetPokemonResponse.getInvalidText() : String? {
    return when {
        isValid() -> null
        species?.name.isNullOrBlank() -> "Pokemon species not found"
        sprites?.frontDefault.isNullOrBlank() -> "Pokemon image not found"
        else -> "Something went wrong"
    }
}

data class Species (
    var name : String?,
    var url : String
)

data class Sprites (
    var backDefault : String,
    var backFemale : String,
    var backShiny : String,
    var backShinyFemale : String,
    var frontDefault : String?,
    var frontFemale : String,
    var frontShiny : String,
    var frontShinyFemale : String,
)