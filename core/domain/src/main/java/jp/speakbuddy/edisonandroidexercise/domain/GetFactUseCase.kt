package jp.speakbuddy.edisonandroidexercise.domain

import jp.speakbuddy.edisonandroidexercise.domain.model.FactEntry
import jp.speakbuddy.edisonandroidexercise.domain.repository.FactRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFactUseCase @Inject constructor(
    private val factRepository: FactRepository,
) {
    operator fun invoke(): Flow<FactEntry> = factRepository.getFact()
}
