package org.sopt.and.domain.usecase

import org.sopt.and.domain.model.Hobby
import org.sopt.and.domain.repository.HobbyRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HobbyUseCase @Inject constructor(
    private val hobbyRepository: HobbyRepository
) {
    suspend operator fun invoke(token: String): Result<Hobby> {
        return hobbyRepository.getMyHobby(token)
    }
}