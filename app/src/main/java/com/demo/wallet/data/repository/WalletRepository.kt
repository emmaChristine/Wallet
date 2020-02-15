package com.demo.wallet.data.repository

import com.demo.wallet.api.WalletAPI
import com.demo.wallet.data.dto.TransactionResponse
import com.demo.wallet.data.dto.txs
import com.demo.wallet.db.WalletDao
import io.reactivex.Completable
import io.reactivex.Flowable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


open class WalletRepository @Inject constructor(
    val walletDao: WalletDao,
    val api: WalletAPI
): BaseRepository() {

    // region remote

    suspend fun loadAllTransactions() : TransactionResponse? = withContext(Dispatchers.IO) {

        return@withContext safeApiCall(
            call = { api.getAllTransactions().await()},
            errorMessage = "Error Fetching Transactions")

    }

    // endregion remote

    // region local

    fun insertAllTransactions(list: List<txs>): Completable {
        return walletDao.insertAllTransactions(list)
    }

    fun fetchAllFromLocal(): Flowable<List<txs>> {
        return walletDao.getAllTransactions()
    }

    // endregion local


}
