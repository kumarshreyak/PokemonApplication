//[pokemonLibrary](../../../index.md)/[com.shrek.pokemonlibrary.network.data.models](../index.md)/[PokemonApiResult](index.md)

# PokemonApiResult

[androidJvm]\
data class [PokemonApiResult](index.md)&lt;[T](index.md)&gt;(resultState: [ResultState](../../com.shrek.pokemonlibrary.network.api/-result-state/index.md), error: [PokemonError](../-pokemon-error/index.md)?, result: [T](index.md)?)

This is a wrapper class which will be used to return result after any SDK call from [PokemonClient](../../com.shrek.pokemonlibrary.client/-pokemon-client/index.md)

## Parameters

androidJvm

| | |
|---|---|
| resultState | [ResultState](../../com.shrek.pokemonlibrary.network.api/-result-state/index.md) State of sdk call, one of - [ResultState.IN_PROGRESS](../../com.shrek.pokemonlibrary.network.api/-result-state/-i-n_-p-r-o-g-r-e-s-s/index.md), [ResultState.SUCCESS](../../com.shrek.pokemonlibrary.network.api/-result-state/-s-u-c-c-e-s-s/index.md), [ResultState.ERROR](../../com.shrek.pokemonlibrary.network.api/-result-state/-e-r-r-o-r/index.md) |
| error | Error after completion of sdk call. @see [PokemonError](../-pokemon-error/index.md) |
| result | Result after sdk call. eg. for [searchPokemonShakespeareDescription](../../com.shrek.pokemonlibrary.client/-pokemon-client/search-pokemon-shakespeare-description.md) - [PokemonApiResult](index.md)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)> is returned |

## Constructors

| | |
|---|---|
| [PokemonApiResult](-pokemon-api-result.md) | [androidJvm]<br>fun &lt;[T](index.md)&gt; [PokemonApiResult](-pokemon-api-result.md)(resultState: [ResultState](../../com.shrek.pokemonlibrary.network.api/-result-state/index.md) = ResultState.IN_PROGRESS, error: [PokemonError](../-pokemon-error/index.md)? = null, result: [T](index.md)? = null) |

## Functions

| Name | Summary |
|---|---|
| [isError](is-error.md) | [androidJvm]<br>fun [isError](is-error.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isInProgress](is-in-progress.md) | [androidJvm]<br>fun [isInProgress](is-in-progress.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isSuccess](is-success.md) | [androidJvm]<br>fun [isSuccess](is-success.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isSuccessNonNull](is-success-non-null.md) | [androidJvm]<br>fun [isSuccessNonNull](is-success-non-null.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

## Properties

| Name | Summary |
|---|---|
| [error](error.md) | [androidJvm]<br>var [error](error.md): [PokemonError](../-pokemon-error/index.md)? = null |
| [result](result.md) | [androidJvm]<br>var [result](result.md): [T](index.md)? = null |
| [resultState](result-state.md) | [androidJvm]<br>var [resultState](result-state.md): [ResultState](../../com.shrek.pokemonlibrary.network.api/-result-state/index.md) |
