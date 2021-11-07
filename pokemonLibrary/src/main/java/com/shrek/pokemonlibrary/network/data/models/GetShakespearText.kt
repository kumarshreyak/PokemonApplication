package com.shrek.pokemonlibrary.network.data.models

class GetShakespeareTextResponse(
    val success : SuccessProps?,
    val contents: ContentProps?,
) {
    class SuccessProps(val total: Int?)
    class ContentProps(val translated: String?, val text: String?, val translation: String?)
}

class GetShakespeareTextRequest(val text : String)