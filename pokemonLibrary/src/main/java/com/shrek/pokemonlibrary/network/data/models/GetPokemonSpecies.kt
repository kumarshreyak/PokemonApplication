package com.shrek.pokemonlibrary.network.data.request

data class GetPokemonSpeciesResponse (
    var id : Int,
    var name : String,
    var flavorTextEntries : List<FlavorTextEntries>,
) {
    companion object {
        const val LANGUAGE_ENGLISH = "english"
    }
}

data class FlavorTextEntries (
    var flavorText : String,
    var language : Language,
)

data class Language (
    var name : String,
    var url : String
)

fun GetPokemonSpeciesResponse.getEnglishDescription() = flavorTextEntries.firstOrNull { it.language.name == GetPokemonSpeciesResponse.LANGUAGE_ENGLISH }?.flavorText
