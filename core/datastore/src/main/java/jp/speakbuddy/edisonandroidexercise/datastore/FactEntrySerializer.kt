package jp.speakbuddy.edisonandroidexercise.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import jp.speakbuddy.edisonandroidexercise.datastore.FactEntryOuterClass.FactEntry
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class FactEntrySerializer @Inject constructor() : Serializer<FactEntry> {
    override val defaultValue: FactEntry = FactEntry.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): FactEntry {
        try {
            return FactEntry.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: FactEntry, output: OutputStream) {
        t.writeTo(output)
    }
}
