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

If you already use compose you can simple include the UI in your composable function, just pass the updated search text in the parameters -
```
@Composable
fun YourComposableFunction() {
	...
	PokemonSpriteUI(searchText)
	...
}
```

If you want to use a Compose in your XML file, you can add this to your layout file:
```
<androidx.compose.ui.platform.ComposeView
    android:id="@+id/my_composable"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
```

Then just set pokemonLibrary's composable on this view -
```
findViewById<ComposeView>(R.id.my_composable).setContent {
	PokemonSpriteUI(searchText)
}
```
