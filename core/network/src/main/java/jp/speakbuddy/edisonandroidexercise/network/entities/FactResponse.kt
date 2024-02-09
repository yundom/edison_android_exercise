package jp.speakbuddy.edisonandroidexercise.network.entities

import kotlinx.serialization.Serializable

@Serializable
data class FactResponse(
    val fact: String,
    val length: Int
)
