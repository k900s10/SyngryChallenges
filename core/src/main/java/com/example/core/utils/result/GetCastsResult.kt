package com.example.core.utils.result

import com.example.core.domain.model.CastsModel

sealed class GetCastsResult<out R> {
    data class Success<T>(val data: List<CastsModel>) : GetCastsResult<T>()

    data object Failed : GetCastsResult<Nothing>()
}
