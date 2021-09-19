package io.afdon.add.model

import java.lang.Exception

sealed class ReqResult<T> {

    class Loading<S>(val isLoading: Boolean) : ReqResult<S>()

    class Success<S>(val data: S) : ReqResult<S>()

    class Error<S>(val error: String, e: Exception? = null) : ReqResult<S>()
}