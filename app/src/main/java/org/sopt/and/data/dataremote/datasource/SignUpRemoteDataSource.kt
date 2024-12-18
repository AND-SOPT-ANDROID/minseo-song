package org.sopt.and.data.dataremote.datasource

import org.sopt.and.data.dataremote.model.request.RequestUserDto
import org.sopt.and.data.dataremote.model.response.BaseResponse
import org.sopt.and.data.dataremote.model.response.ResponseUserDto

interface SignUpRemoteDataSource {
    suspend fun postUser(requestUserDto: RequestUserDto): BaseResponse<ResponseUserDto>
}