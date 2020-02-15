package com.demo.wallet.api

import com.demo.wallet.data.dto.TransactionResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

//retrofit Network Interface for the Api

interface WalletAPI {

    @GET("multiaddr?&n=5")
    fun getAllTransactions(): Deferred<Response<TransactionResponse>>

}
