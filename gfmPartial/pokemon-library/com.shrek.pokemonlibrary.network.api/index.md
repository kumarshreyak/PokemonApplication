//[pokemonLibrary](../../index.md)/[com.shrek.pokemonlibrary.network.api](index.md)

# Package com.shrek.pokemonlibrary.network.api

## Types

| Name | Summary |
|---|---|
| [ApiResult](-api-result/index.md) | [androidJvm]<br>data class [ApiResult](-api-result/index.md)&lt;[T](-api-result/index.md)&gt;(resultState: [ResultState](-result-state/index.md), errorMessage: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)?, result: [T](-api-result/index.md)?) |
| [NetworkCall](index.md#1552257768%2FClasslikes%2F1643807906) | [androidJvm]<br>typealias [NetworkCall](index.md#1552257768%2FClasslikes%2F1643807906)&lt;[T](index.md#1552257768%2FClasslikes%2F1643807906)&gt; = suspend () -&gt; <!---  GfmCommand {"@class":"org.jetbrains.dokka.gfm.ResolveLinkGfmCommand","dri":{"packageName":"retrofit2","classNames":"Response","callable":null,"target":{"@class":"org.jetbrains.dokka.links.PointingToDeclaration"},"extra":null}} --->Response<!--- --->&lt;[T](index.md#1552257768%2FClasslikes%2F1643807906)&gt; |
| [ResultState](-result-state/index.md) | [androidJvm]<br>enum [ResultState](-result-state/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[ResultState](-result-state/index.md)&gt; |

## Functions

| Name | Summary |
|---|---|
| [apiCall](api-call.md) | [androidJvm]<br>suspend fun &lt;[T](api-call.md) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt; [apiCall](api-call.md)(block: [NetworkCall](index.md#1552257768%2FClasslikes%2F1643807906)&lt;[T](api-call.md)&gt;): [ApiResult](-api-result/index.md)&lt;[T](api-call.md)&gt; |
| [create](create.md) | [androidJvm]<br>inline fun &lt;[T](create.md)&gt; [create](create.md)(): [T](create.md) |

## Properties

| Name | Summary |
|---|---|
| [retrofit](retrofit.md) | [androidJvm]<br>val [retrofit](retrofit.md): <!---  GfmCommand {"@class":"org.jetbrains.dokka.gfm.ResolveLinkGfmCommand","dri":{"packageName":"retrofit2","classNames":"Retrofit","callable":null,"target":{"@class":"org.jetbrains.dokka.links.PointingToDeclaration"},"extra":null}} --->Retrofit<!--- ---> |
| [retrofitGson](retrofit-gson.md) | [androidJvm]<br>val [retrofitGson](retrofit-gson.md): <!---  GfmCommand {"@class":"org.jetbrains.dokka.gfm.ResolveLinkGfmCommand","dri":{"packageName":"com.google.gson","classNames":"Gson","callable":null,"target":{"@class":"org.jetbrains.dokka.links.PointingToDeclaration"},"extra":null}} --->Gson<!--- ---> |
| [TIMEOUT](-t-i-m-e-o-u-t.md) | [androidJvm]<br>val [TIMEOUT](-t-i-m-e-o-u-t.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 10L |
