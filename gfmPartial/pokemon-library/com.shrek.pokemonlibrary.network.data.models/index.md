//[pokemonLibrary](../../index.md)/[com.shrek.pokemonlibrary.network.data.models](index.md)

# Package com.shrek.pokemonlibrary.network.data.models

## Types

| Name | Summary |
|---|---|
| [ApiError](-api-error/index.md) | [androidJvm]<br>data class [ApiError](-api-error/index.md)(error: [ApiError.ErrorProp](-api-error/-error-prop/index.md)) : [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html) |
| [PokemonApiResult](-pokemon-api-result/index.md) | [androidJvm]<br>data class [PokemonApiResult](-pokemon-api-result/index.md)&lt;[T](-pokemon-api-result/index.md)&gt;(resultState: [ResultState](../com.shrek.pokemonlibrary.network.api/-result-state/index.md), error: [PokemonError](-pokemon-error/index.md)?, result: [T](-pokemon-api-result/index.md)?)<br>This is a wrapper class which will be used to return result after any SDK call from [PokemonClient](../com.shrek.pokemonlibrary.client/-pokemon-client/index.md) |
| [PokemonError](-pokemon-error/index.md) | [androidJvm]<br>data class [PokemonError](-pokemon-error/index.md)(errorMessage: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, httpFailureCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)?) |
| [PokemonShakespeareDescription](-pokemon-shakespeare-description/index.md) | [androidJvm]<br>data class [PokemonShakespeareDescription](-pokemon-shakespeare-description/index.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), description: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), imgUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [PokemonSprite](-pokemon-sprite/index.md) | [androidJvm]<br>data class [PokemonSprite](-pokemon-sprite/index.md)(imgUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>This class represents response of [searchPokemonShakespeareDescription](../com.shrek.pokemonlibrary.client/-pokemon-client/search-pokemon-sprite.md) sdk call. |

## Functions

| Name | Summary |
|---|---|
| [toPokemonApiResult](to-pokemon-api-result.md) | [androidJvm]<br>fun &lt;[T](to-pokemon-api-result.md)&gt; [ApiResult](../com.shrek.pokemonlibrary.network.api/-api-result/index.md)&lt;[T](to-pokemon-api-result.md)&gt;.[toPokemonApiResult](to-pokemon-api-result.md)(): [PokemonApiResult](-pokemon-api-result/index.md)&lt;[T](to-pokemon-api-result.md)&gt; |
| [toPokemonError](to-pokemon-error.md) | [androidJvm]<br>fun [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html).[toPokemonError](to-pokemon-error.md)(): [PokemonError](-pokemon-error/index.md) |
