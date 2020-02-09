package com.belzsoftware.futspect.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.belzsoftware.futspect.model.shared.Result
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

inline fun <reified VM : ViewModel> Fragment.viewModelProvider(
    provider: ViewModelProvider.Factory
) =
    ViewModelProvider(this, provider).get(VM::class.java)

fun <T> resultLiveData(networkCall: suspend () -> Result<T>): LiveData<Result<T>> =
    liveData(Dispatchers.IO) {
        emit(Result.loading())

        val result = networkCall.invoke()
        emit(result)
    }

suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
    try {
        val response = call()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) return Result.success(body)
        }

        return Result.error("${response.code()} ${response.message()}")
    } catch (e: Exception) {
        return Result.error(e.message ?: e.toString())
    }
}

fun <T> defaultResult() =
    MutableLiveData<Result<T>>(Result.loading())