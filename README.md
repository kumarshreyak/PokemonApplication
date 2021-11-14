# PokemonApplication
This project contains `pokemonLibrary` which is a sdk that helps you search for pokemon names, get their image and description in a Shakespeare-esque translation.

## How to add pokemonLibrary to your project
Add `jitpack` dependency to your project-level build.gradle file
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

Add `pokemonLibrary` to your app-level build.gradle file.
```
dependencies {
    implementation 'com.github.kumarshreyak.PokemonApplication:pokemonlibrary-release:1.0.2'
}
```
To find our the latest release version please check out the [Releases section](https://github.com/kumarshreyak/PokemonApplication/releases) on this repo.

## Usage
Refer [PokemonClient](https://github.com/kumarshreyak/PokemonApplication/blob/master/gfmPartial/pokemon-library/com.shrek.pokemonlibrary.client/index.md) for detailed documentation

Example - 
All UI in library is made in Jetpack Compose ❤️ 🚀

If you already use compose you can simple include the UI in your composable function, just pass the updated search text in the parameters, like so -
```
@Composable
fun YourComposableFunction() {
	...
	PokemonSpriteUI(searchText)
	...
}
```
