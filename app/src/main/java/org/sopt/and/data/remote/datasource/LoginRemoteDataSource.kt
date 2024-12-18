package org.sopt.and.data.remote.datasource

import org.sopt.and.data.remote.model.request.RequestLoginDto
import org.sopt.and.data.remote.model.response.BaseResponse
import org.sopt.and.data.remote.model.response.ResponseLoginDto

interface LoginRemoteDataSource {
    suspend fun postLogin(requestLoginDto: RequestLoginDto): BaseResponse<ResponseLoginDto>
}