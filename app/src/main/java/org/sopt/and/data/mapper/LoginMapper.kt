package org.sopt.and.data.mapper

import org.sopt.and.data.remote.model.response.BaseResponse
import org.sopt.and.data.remote.model.response.ResponseLoginDto
import org.sopt.and.domain.model.Token

fun BaseResponse<ResponseLoginDto>.toDomain(): Token {
    return Token(token = this.result.token)
}
