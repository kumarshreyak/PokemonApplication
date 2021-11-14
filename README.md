# PokemonApplication
This project contains `pokemonLibrary` which is a sdk that helps you search for pokemon names, get their image and description in a Shakespeare-esque translation.

## How to add `pokemonLibrary` to your project
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
    implementation 'com.github.kumarshreyak.PokemonApplication:pokemonlibrary-release:1.0.3'
}
```
To find our the latest release version please check out the [Releases section](https://github.com/kumarshreyak/PokemonApplication/releases) on this repo.

## How to use `pokemonLibrary`
All UI in this library is made in Jetpack Compose ❤️ 🚀

Refer [PokemonClient](https://github.com/kumarshreyak/PokemonApplication/blob/master/gfmPartial/pokemon-library/com.shrek.pokemonlibrary.client/index.md) for the SDK javadoc.

Refer complete javadoc [here](https://github.com/kumarshreyak/PokemonApplication/blob/master/gfmPartial/index.md)

For example usage in an app please refer the [example app](https://github.com/kumarshreyak/PokemonApplication/tree/master/app) in this project.


If you already use compose you can simply include the UI in your composable function -
```
@Composable
fun YourComposableFunction() {
	...
	PokemonShakespeareDescriptionUI(searchText = searchText)
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

Then just set `pokemonLibrary`'s composable on this view -
```
findViewById<ComposeView>(R.id.my_composable).setContent {
	PokemonSpriteUI(searchText = searchText)
}
```

UI's layout can be modified like this -
```
PokemonSpriteUI(
    searchText = searchText,
    modifier = Modifier
	.align(Alignment.CenterHorizontally),
    imageModifier = Modifier
	.size(200.dp)
	.align(Alignment.CenterHorizontally)
)
```
