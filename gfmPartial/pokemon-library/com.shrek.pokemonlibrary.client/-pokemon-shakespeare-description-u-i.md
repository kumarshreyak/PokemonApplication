//[pokemonLibrary](../../index.md)/[com.shrek.pokemonlibrary.client](index.md)/[PokemonShakespeareDescriptionUI](-pokemon-shakespeare-description-u-i.md)

# PokemonShakespeareDescriptionUI

[androidJvm]\

@<!---  GfmCommand {"@class":"org.jetbrains.dokka.gfm.ResolveLinkGfmCommand","dri":{"packageName":"androidx.compose.runtime","classNames":"Composable","callable":null,"target":{"@class":"org.jetbrains.dokka.links.PointingToDeclaration"},"extra":null}} --->Composable<!--- --->

fun [PokemonShakespeareDescriptionUI](-pokemon-shakespeare-description-u-i.md)(searchText: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, showProgressLoader: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, showSearchFailure: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true)

UI to display Pokemon's shakespeare description. This UI internally manages the search and progress/error states all you need to provide is the [searchText](-pokemon-shakespeare-description-u-i.md). The state of the search performed here can be observed by observing [PokemonClient.descriptionResponse](-pokemon-client/description-response.md) livedata.

## Parameters

androidJvm

| | |
|---|---|
| searchText | Pokemon name to search for. |
