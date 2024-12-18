package org.sopt.and.data.repositoryimpl

import org.sopt.and.data.mapper.toDomain
import org.sopt.and.data.remote.datasource.HobbyRemoteDataSource
import org.sopt.and.domain.model.Hobby
import org.sopt.and.domain.repository.HobbyRepository
import javax.inject.Inject

class HobbyRepositoryImpl @Inject constructor(
    private val hobbyRemoteDataSource: HobbyRemoteDataSource
) : HobbyRepository {
    override suspend fun getMyHobby(token: String): Result<Hobby> =
        runCatching {
            val response = hobbyRemoteDataSource.getMyHobby(token)
            response.toDomain()
        }
}