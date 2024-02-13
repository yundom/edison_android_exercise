package jp.speakbuddy.edisonandroidexercise.data.repository

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import jp.speakbuddy.edisonandroidexercise.datastore.FactLocalDataSource
import jp.speakbuddy.edisonandroidexercise.domain.model.Fact
import jp.speakbuddy.edisonandroidexercise.network.FactRemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class OfflineFirstFactRepositoryTest {

    private lateinit var remoteDataSource: FactRemoteDataSource
    private lateinit var localDataSource: FactLocalDataSource
    private lateinit var repository: OfflineFirstFactRepository

    @Before
    fun setUp() {
        remoteDataSource = mockk()
        localDataSource = mockk()

        repository = OfflineFirstFactRepository(remoteDataSource, localDataSource)
    }

    @Test
    fun `getFact emits remote fact when remote data is valid`() = runTest {
        val fact = Fact("Test fact", 10)
        coEvery { remoteDataSource.getFact() } returns fact
        coEvery { localDataSource.setFact(any()) } returns Unit

        val result = repository.getFact().first()

        assertEquals(fact, result)
        coVerify(exactly = 1) { localDataSource.setFact(fact) }
    }

    @Test
    fun `getFact emits local fact when remote call fails`() = runTest {
        val remoteException = RuntimeException("Remote data source failure")
        val localFact = Fact("Local fact", 5)
        coEvery { remoteDataSource.getFact() } throws remoteException
        coEvery { localDataSource.getFact() } returns localFact

        val result = repository.getFact().first()

        assertEquals(localFact, result)
    }

    @Test
    fun `getFact emits message when both local and remote data are not available`() = runTest {
        val remoteException = RuntimeException("Remote data source failure")
        coEvery { remoteDataSource.getFact() } throws remoteException
        coEvery { localDataSource.getFact() } returns Fact("", 0)

        val result = repository.getFact().first()

        val msg = "Can't load facts."
        assertEquals(Fact(msg, msg.length), result)
    }
}
