//[pokemonLibrary](../../../index.md)/[com.shrek.pokemonlibrary.client](../index.md)/[PokemonClient](index.md)/[searchPokemonShakespeareDescription](search-pokemon-shakespeare-description.md)

# searchPokemonShakespeareDescription

[androidJvm]\
suspend fun [searchPokemonShakespeareDescription](search-pokemon-shakespeare-description.md)(pokemonName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [PokemonApiResult](../../com.shrek.pokemonlibrary.network.data.models/-pokemon-api-result/index.md)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;

Function to search for Shakespeare description of a Pokemon. This is a suspending function which is safe to call from the main thread, it internally uses the IO thread to make the API call.

#### Return

[PokemonApiResult](../../com.shrek.pokemonlibrary.network.data.models/-pokemon-api-result/index.md)<String> search result for given pokemon name.

## Parameters

androidJvm

| | |
|---|---|
| pokemonName | Pokemon name to search for |
