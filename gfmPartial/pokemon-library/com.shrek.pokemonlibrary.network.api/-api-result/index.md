//[pokemonLibrary](../../../index.md)/[com.shrek.pokemonlibrary.network.api](../index.md)/[ApiResult](index.md)

# ApiResult

[androidJvm]\
data class [ApiResult](index.md)&lt;[T](index.md)&gt;(resultState: [ResultState](../-result-state/index.md), errorMessage: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)?, result: [T](index.md)?)

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [error](error.md) | [androidJvm]<br>fun [error](error.md)(t: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)?, message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?): [ApiResult](index.md)&lt;[T](index.md)&gt; |
| [errorMessage](error-message.md) | [androidJvm]<br>fun [errorMessage](error-message.md)(default: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [isError](is-error.md) | [androidJvm]<br>fun [isError](is-error.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isInProgress](is-in-progress.md) | [androidJvm]<br>fun [isInProgress](is-in-progress.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isSuccess](is-success.md) | [androidJvm]<br>fun [isSuccess](is-success.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [success](success.md) | [androidJvm]<br>fun [success](success.md)(result: [T](index.md)): [ApiResult](index.md)&lt;[T](index.md)&gt; |

## Properties

| Name | Summary |
|---|---|
| [error](error.md) | [androidJvm]<br>val [error](error.md): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)? = null |
| [errorMessage](error-message.md) | [androidJvm]<br>val [errorMessage](error-message.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [result](result.md) | [androidJvm]<br>val [result](result.md): [T](index.md)? = null |
| [resultState](result-state.md) | [androidJvm]<br>val [resultState](result-state.md): [ResultState](../-result-state/index.md) |

## Extensions

| Name | Summary |
|---|---|
| [toPokemonApiResult](../../com.shrek.pokemonlibrary.network.data.models/to-pokemon-api-result.md) | [androidJvm]<br>fun &lt;[T](../../com.shrek.pokemonlibrary.network.data.models/to-pokemon-api-result.md)&gt; [ApiResult](index.md)&lt;[T](../../com.shrek.pokemonlibrary.network.data.models/to-pokemon-api-result.md)&gt;.[toPokemonApiResult](../../com.shrek.pokemonlibrary.network.data.models/to-pokemon-api-result.md)(): [PokemonApiResult](../../com.shrek.pokemonlibrary.network.data.models/-pokemon-api-result/index.md)&lt;[T](../../com.shrek.pokemonlibrary.network.data.models/to-pokemon-api-result.md)&gt; |
