package com.belzsoftware.futspect.model.shared

sealed class Result<out T> {
    class loading<T> : Result<T>()
    data class error<T>(val message: String) : Result<T>()
    data class success<T>(val data: T) : Result<T>()
}