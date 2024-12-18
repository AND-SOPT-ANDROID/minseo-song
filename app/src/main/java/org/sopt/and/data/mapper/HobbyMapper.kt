package org.sopt.and.data.mapper

import org.sopt.and.data.remote.model.response.BaseResponse
import org.sopt.and.data.remote.model.response.ResponseHobbyDto
import org.sopt.and.domain.model.Hobby

fun BaseResponse<ResponseHobbyDto>.toDomain(): Hobby {
    return Hobby(hobby = this.result.hobby)
}
