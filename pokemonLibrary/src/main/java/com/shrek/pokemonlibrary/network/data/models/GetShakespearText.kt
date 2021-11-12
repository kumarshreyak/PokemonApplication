package com.shrek.pokemonlibrary.network.data.models

internal class GetShakespeareTextResponse(
    val success : SuccessProps?,
    val contents: ContentProps?,
) {
    class SuccessProps(val total: Int?)
    class ContentProps(val translated: String?, val text: String?, val translation: String?)
}

internal class GetShakespeareTextRequest(val text : String)