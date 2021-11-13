package com.shrek.pokemonlibrary.network.data.models

internal data class GetPokemonSpeciesResponse (
    var id : Int,
    var name : String,
    var flavorTextEntries : List<FlavorTextEntries>,
) {
    companion object {
        const val LANGUAGE_ENGLISH = "en"
    }
}

internal data class FlavorTextEntries (
    var flavorText : String,
    var language : Language,
)

internal data class Language (
    var name : String,
    var url : String
)

internal fun GetPokemonSpeciesResponse.getEnglishDescription() = flavorTextEntries?.firstOrNull { it.language.name == GetPokemonSpeciesResponse.LANGUAGE_ENGLISH }?.flavorText
