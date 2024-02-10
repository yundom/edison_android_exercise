package jp.speakbuddy.edisonandroidexercise.data.mapper

import jp.speakbuddy.edisonandroidexercise.domain.model.FactEntry
import jp.speakbuddy.edisonandroidexercise.network.entities.FactResponse

fun FactResponse.toFactEntry(): FactEntry = FactEntry(this.fact, this.length)
