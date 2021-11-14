# PokemonApplication
This project contains `pokemonLibrary` which is a sdk that helps you search for pokemon names, get their image and description in a Shakespeare-esque translation.

## How to use
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
To find our the latest release version please check out the `Releases` section on this repo.

Javadoc for exposed data classes / methods -
