package org.sopt.and.data.repositoryimpl

import org.sopt.and.data.dataremote.datasource.HobbyRemoteDataSource
import org.sopt.and.data.dataremote.model.response.BaseResponse
import org.sopt.and.data.dataremote.model.response.ResponseHobbyDto
import org.sopt.and.domain.repository.HobbyRepository
import javax.inject.Inject

class HobbyRepositoryImpl @Inject constructor(
    private val hobbyRemoteDataSource: HobbyRemoteDataSource
): HobbyRepository{
    override suspend fun getMyHobby(token: String): Result<BaseResponse<ResponseHobbyDto>> = runCatching{
        hobbyRemoteDataSource.getMyHobby(token)
    }
}