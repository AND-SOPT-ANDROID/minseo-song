package org.sopt.and.data.mapper

import org.sopt.and.data.remote.model.response.BaseResponse
import org.sopt.and.data.remote.model.response.ResponseUserDto
import org.sopt.and.domain.model.User

fun BaseResponse<ResponseUserDto>.toDomain(): User {
    return User(userNumber = this.result.userNumber)
}
