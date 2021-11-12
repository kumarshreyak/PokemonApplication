package com.shrek.pokemonlibrary.network.data.models

/**
 * This class represents response of [searchPokemonShakespeareDescription][com.shrek.pokemonlibrary.client.PokemonClient.searchPokemonSprite] sdk call.
 * @property [imgUrl] Complete URL of pokemon's image.
 */
data class PokemonSprite(val imgUrl: String)