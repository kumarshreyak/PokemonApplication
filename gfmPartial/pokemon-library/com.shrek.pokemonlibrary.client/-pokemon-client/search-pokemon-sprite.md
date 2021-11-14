//[pokemonLibrary](../../../index.md)/[com.shrek.pokemonlibrary.client](../index.md)/[PokemonClient](index.md)/[searchPokemonSprite](search-pokemon-sprite.md)

# searchPokemonSprite

[androidJvm]\
suspend fun [searchPokemonSprite](search-pokemon-sprite.md)(pokemonName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [PokemonApiResult](../../com.shrek.pokemonlibrary.network.data.models/-pokemon-api-result/index.md)&lt;[PokemonSprite](../../com.shrek.pokemonlibrary.network.data.models/-pokemon-sprite/index.md)&gt;

Function to search for sprite (image) of a Pokemon. This is a suspending function which is safe to call from main thread, it internally uses the IO thread to make the API call.

#### Return

[PokemonApiResult](../../com.shrek.pokemonlibrary.network.data.models/-pokemon-api-result/index.md)<[PokemonSprite](../../com.shrek.pokemonlibrary.network.data.models/-pokemon-sprite/index.md)> search result for given pokemon name.

## Parameters

androidJvm

| | |
|---|---|
| pokemonName | Pokemon name to search for |
