package com.belzsoftware.futspect.util.adapters

// experimental adapter to convert nulls to empty strings
//import androidx.annotation.Nullable
//import com.squareup.moshi.FromJson
//import com.squareup.moshi.JsonQualifier
//import com.squareup.moshi.JsonReader
//import com.squareup.moshi.ToJson
//
//@Retention(AnnotationRetention.RUNTIME)
//@JsonQualifier
//annotation class NullToEmptyString
//
//class NullToEmptyStringAdapter {
//    @ToJson
//    fun toJson(@NullToEmptyString value: String?): String? {
//        return value
//    }
//
//    @FromJson
//    @NullToEmptyString
//    fun fromJson(@Nullable reader: JsonReader): String? {
//        val result = if (reader.peek() === JsonReader.Token.NULL) {
//            reader.nextNull()
//        } else {
//            reader.nextString()
//        }
//
//        return result ?: ""    }
//}