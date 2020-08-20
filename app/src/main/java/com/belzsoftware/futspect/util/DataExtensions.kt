package com.belzsoftware.futspect.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.belzsoftware.futspect.model.shared.Result
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

fun <T> resultLiveData(networkCall: suspend () -> Result<T>): LiveData<Result<T>> =
    liveData(Dispatchers.IO) {
        emit(Result.Loading())

        val result = networkCall.invoke()
        emit(result)
    }

suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
    try {
        val response = call()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) return Result.Success(body)
        }

        return Result.Error("${response.code()} ${response.message()}")
    } catch (e: Exception) {
        return Result.Error(e.message ?: e.toString())
    }
}

fun <T> defaultResult() =
    MutableLiveData<Result<T>>(Result.Loading())