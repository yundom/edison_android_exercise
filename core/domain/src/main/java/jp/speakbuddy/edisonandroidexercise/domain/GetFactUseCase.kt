package jp.speakbuddy.edisonandroidexercise.domain

import jp.speakbuddy.edisonandroidexercise.domain.model.FactWithCats
import jp.speakbuddy.edisonandroidexercise.domain.repository.FactRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFactUseCase @Inject constructor(
    private val factRepository: FactRepository,
) {
    private val pattern = Regex("(?i)cats")

    operator fun invoke(): Flow<FactWithCats> =
        factRepository.getFact().map {
            FactWithCats(it, pattern.containsMatchIn(it.fact))
        }
}
