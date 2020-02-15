package com.demo.wallet.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.demo.wallet.data.dto.txs
import io.reactivex.Completable
import io.reactivex.Flowable


// insert & get crypto
// uses reactive queries with RxJava

@Dao
interface WalletDao {

    @Query("SELECT * FROM txs ORDER BY time")
    fun getAllTransactions(): Flowable<List<txs>>


    @Insert(onConflict = REPLACE)
    fun insertAllTransactions(list :List<txs>): Completable

}