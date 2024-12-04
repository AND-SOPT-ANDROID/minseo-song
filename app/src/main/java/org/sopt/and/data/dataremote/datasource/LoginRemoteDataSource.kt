package org.sopt.and.data.dataremote.datasource

import org.sopt.and.data.dataremote.model.request.RequestLoginDto
import org.sopt.and.data.dataremote.model.response.BaseResponse
import org.sopt.and.data.dataremote.model.response.ResponseLoginDto

interface LoginRemoteDataSource{
    suspend fun postLogin(requestLoginDto: RequestLoginDto): BaseResponse<ResponseLoginDto>
}