//[pokemonLibrary](../../../index.md)/[com.shrek.pokemonlibrary.network.data.models](../index.md)/[PokemonApiResult](index.md)/[PokemonApiResult](-pokemon-api-result.md)

# PokemonApiResult

[androidJvm]\
fun &lt;[T](index.md)&gt; [PokemonApiResult](-pokemon-api-result.md)(resultState: [ResultState](../../com.shrek.pokemonlibrary.network.api/-result-state/index.md) = ResultState.IN_PROGRESS, error: [PokemonError](../-pokemon-error/index.md)? = null, result: [T](index.md)? = null)

## Parameters

androidJvm

| | |
|---|---|
| resultState | [ResultState](../../com.shrek.pokemonlibrary.network.api/-result-state/index.md) State of sdk call, one of - [ResultState.IN_PROGRESS](../../com.shrek.pokemonlibrary.network.api/-result-state/-i-n_-p-r-o-g-r-e-s-s/index.md), [ResultState.SUCCESS](../../com.shrek.pokemonlibrary.network.api/-result-state/-s-u-c-c-e-s-s/index.md), [ResultState.ERROR](../../com.shrek.pokemonlibrary.network.api/-result-state/-e-r-r-o-r/index.md) |
| error | Error after completion of sdk call. @see [PokemonError](../-pokemon-error/index.md) |
| result | Result after sdk call. eg. for [searchPokemonShakespeareDescription](../../com.shrek.pokemonlibrary.client/-pokemon-client/search-pokemon-shakespeare-description.md) - [PokemonApiResult](index.md)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)> is returned |
