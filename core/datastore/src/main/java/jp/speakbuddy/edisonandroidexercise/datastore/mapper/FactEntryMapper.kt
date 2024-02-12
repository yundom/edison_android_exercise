package jp.speakbuddy.edisonandroidexercise.datastore.mapper

import jp.speakbuddy.edisonandroidexercise.datastore.FactEntryOuterClass.FactEntry
import jp.speakbuddy.edisonandroidexercise.domain.model.Fact

fun FactEntry.toFact(): Fact = Fact(this.fact, this.length)
