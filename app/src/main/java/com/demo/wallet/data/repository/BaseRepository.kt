package com.demo.wallet.data.repository


import retrofit2.Response
import timber.log.Timber
import java.io.IOException

// handle safeApiCall
open class BaseRepository{

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {

        val result : Result<T> = safeApiResult(call,errorMessage)
        var data : T? = null

        when(result) {
            is Result.Success -> {
                data = result.data
            }
            is Result.Error -> {
                Timber.e("$errorMessage & Exception - ${result.exception}")
            }
        }


        return data

    }

    private suspend fun <T: Any> safeApiResult(call: suspend ()-> Response<T>, errorMessage: String) : Result<T>{

        try {
            val response = call.invoke()

            if(response.isSuccessful)
                return Result.Success(response.body()!!)
        }catch(e: IOException) {
            return Result.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
        }

        return Result.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
    }
}