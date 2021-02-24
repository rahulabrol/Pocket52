package com.rahulabrol.pocket52test.util

import com.google.gson.Gson
import com.rahulabrol.pocket52test.model.APIError
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.net.SocketTimeoutException

/**
 * Created by Rahul Abrol on 24/2/21.
 */

class Utils {
    companion object {
        fun parseError(response: ResponseBody?): String? {
            response?.let {
                val apiError = Gson().fromJson(
                    response.charStream(),
                    APIError::class.java
                )
                return apiError.message
            } ?: return "Parsing error"
        }

        fun parseException(e: Exception): String {
            when (e) {
                is HttpException -> {
                    return "Bad Request"
                }
                is SocketTimeoutException -> {
                    return "Timeout"
                }
                else -> {
                    e.message?.let { return it } ?: return "UnKnown exception"
                }
            }
        }

    }
}