package com.shrek.pokemonlibrary.network.data.models

internal data class GetPokemonResponse (
    var id : Int,
    var name : String,
    var species : Species?,
    var sprites: Sprites?,
) {
    fun hasSprite() = !sprites?.frontDefault.isNullOrBlank()
}

internal data class Species (
    var name : String?,
    var url : String
)

internal data class Sprites (
    var backDefault : String?,
    var backFemale : String?,
    var backShiny : String?,
    var backShinyFemale : String?,
    var frontDefault : String?,
    var frontFemale : String?,
    var frontShiny : String?,
    var frontShinyFemale : String?,
)