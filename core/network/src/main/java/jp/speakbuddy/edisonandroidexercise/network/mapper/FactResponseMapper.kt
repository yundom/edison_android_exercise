package jp.speakbuddy.edisonandroidexercise.network.mapper

import jp.speakbuddy.edisonandroidexercise.domain.model.Fact
import jp.speakbuddy.edisonandroidexercise.network.entities.FactResponse

fun FactResponse.toFact(): Fact = Fact(this.fact, this.length)
