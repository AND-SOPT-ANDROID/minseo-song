package org.sopt.and.data.remote.datasource

import org.sopt.and.data.remote.model.request.RequestUserDto
import org.sopt.and.data.remote.model.response.BaseResponse
import org.sopt.and.data.remote.model.response.ResponseUserDto

interface SignUpRemoteDataSource {
    suspend fun postUser(requestUserDto: RequestUserDto): BaseResponse<ResponseUserDto>
}