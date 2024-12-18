package org.sopt.and.domain.repository

import org.sopt.and.domain.model.Hobby

interface HobbyRepository {
    suspend fun getMyHobby(
        token: String
    ): Result<Hobby>
}