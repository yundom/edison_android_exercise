package jp.speakbuddy.edisonandroidexercise.datastore

import androidx.datastore.core.Serializer
import jp.speakbuddy.edisonandroidexercise.datastore.FactEntryOuterClass.FactEntry
import java.io.InputStream
import java.io.OutputStream

object FactEntrySerializer: Serializer<FactEntry> {
    override val defaultValue: FactEntry
        get() = TODO("Not yet implemented")

    override suspend fun readFrom(input: InputStream): FactEntry {
        TODO("Not yet implemented")
    }

    override suspend fun writeTo(t: FactEntry, output: OutputStream) {
        TODO("Not yet implemented")
    }
}
