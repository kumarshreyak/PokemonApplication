//[pokemonLibrary](../../index.md)/[com.shrek.pokemonlibrary.client](index.md)

# Package com.shrek.pokemonlibrary.client

## Types

| Name | Summary |
|---|---|
| [PokemonClient](-pokemon-client/index.md) | [androidJvm]<br>class [PokemonClient](-pokemon-client/index.md)<br>This class can be used to perform different searches for pokemon names. |

## Functions

| Name | Summary |
|---|---|
| [PokemonShakespeareDescriptionUI](-pokemon-shakespeare-description-u-i.md) | [androidJvm]<br>@<!---  GfmCommand {"@class":"org.jetbrains.dokka.gfm.ResolveLinkGfmCommand","dri":{"packageName":"androidx.compose.runtime","classNames":"Composable","callable":null,"target":{"@class":"org.jetbrains.dokka.links.PointingToDeclaration"},"extra":null}} --->Composable<!--- ---><br>fun [PokemonShakespeareDescriptionUI](-pokemon-shakespeare-description-u-i.md)(searchText: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, showProgressLoader: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, showSearchFailure: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true)<br>UI to display Pokemon's shakespeare description. This UI internally manages the search and progress/error states all you need to provide is the [searchText](-pokemon-shakespeare-description-u-i.md). The state of the search performed here can be observed by observing [PokemonClient.descriptionResponse](-pokemon-client/description-response.md) livedata. |
| [PokemonSpriteUI](-pokemon-sprite-u-i.md) | [androidJvm]<br>@<!---  GfmCommand {"@class":"org.jetbrains.dokka.gfm.ResolveLinkGfmCommand","dri":{"packageName":"androidx.compose.runtime","classNames":"Composable","callable":null,"target":{"@class":"org.jetbrains.dokka.links.PointingToDeclaration"},"extra":null}} --->Composable<!--- ---><br>fun [PokemonSpriteUI](-pokemon-sprite-u-i.md)(searchText: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, showProgressLoader: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, showSearchFailure: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true)<br>UI to display Pokemon's sprite (image). This UI internally manages the search and progress/error states all you need to provide is the [searchText](-pokemon-sprite-u-i.md). The state of the search performed here can be observed by observing [PokemonClient.pokemonSpriteResponse](-pokemon-client/pokemon-sprite-response.md) livedata. |
