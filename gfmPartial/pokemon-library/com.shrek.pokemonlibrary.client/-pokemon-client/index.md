//[pokemonLibrary](../../../index.md)/[com.shrek.pokemonlibrary.client](../index.md)/[PokemonClient](index.md)

# PokemonClient

[androidJvm]\
class [PokemonClient](index.md)

This class can be used to perform different searches for pokemon names.

## See also

androidJvm

| | |
|---|---|
| [com.shrek.pokemonlibrary.client.PokemonClient](search-pokemon-sprite.md) | , [searchPokemonShakespeareDescription](search-pokemon-shakespeare-description.md) |

## Types

| Name | Summary |
|---|---|
| [Builder](-builder/index.md) | [androidJvm]<br>class [Builder](-builder/index.md)(sdkKey: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), appContext: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))<br>Builder class to build [PokemonClient](index.md) |
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [searchPokemonShakespeareDescription](search-pokemon-shakespeare-description.md) | [androidJvm]<br>suspend fun [searchPokemonShakespeareDescription](search-pokemon-shakespeare-description.md)(pokemonName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [PokemonApiResult](../../com.shrek.pokemonlibrary.network.data.models/-pokemon-api-result/index.md)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;<br>Function to search for Shakespeare description of a Pokemon. This is a suspending function which is safe to call from the main thread, it internally uses the IO thread to make the API call. |
| [searchPokemonSprite](search-pokemon-sprite.md) | [androidJvm]<br>suspend fun [searchPokemonSprite](search-pokemon-sprite.md)(pokemonName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [PokemonApiResult](../../com.shrek.pokemonlibrary.network.data.models/-pokemon-api-result/index.md)&lt;[PokemonSprite](../../com.shrek.pokemonlibrary.network.data.models/-pokemon-sprite/index.md)&gt;<br>Function to search for sprite (image) of a Pokemon. This is a suspending function which is safe to call from main thread, it internally uses the IO thread to make the API call. |

## Properties

| Name | Summary |
|---|---|
| [descriptionResponse](description-response.md) | [androidJvm]<br>val [descriptionResponse](description-response.md): [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)&lt;[PokemonApiResult](../../com.shrek.pokemonlibrary.network.data.models/-pokemon-api-result/index.md)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;?&gt;<br>[descriptionResponse](description-response.md) is a Livedata that can be used to observe the response from [searchPokemonShakespeareDescription](search-pokemon-shakespeare-description.md) |
| [pokemonSpriteResponse](pokemon-sprite-response.md) | [androidJvm]<br>val [pokemonSpriteResponse](pokemon-sprite-response.md): [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)&lt;[PokemonApiResult](../../com.shrek.pokemonlibrary.network.data.models/-pokemon-api-result/index.md)&lt;[PokemonSprite](../../com.shrek.pokemonlibrary.network.data.models/-pokemon-sprite/index.md)&gt;?&gt;<br>[descriptionResponse](description-response.md) is a Livedata that can be used to observe the response from [searchPokemonShakespeareDescription](search-pokemon-shakespeare-description.md)` |
