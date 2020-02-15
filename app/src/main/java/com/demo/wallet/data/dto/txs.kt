package com.demo.wallet.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "txs")
data class txs(
    @PrimaryKey
    @SerializedName("hash")
    val hash: String,
    @SerializedName("result")
    // As an improvement BigInteger should be used.
    val result: Long,
    @SerializedName("time")
    val time: Long,
    @SerializedName("fee")
    val fee: Long?) {

    override fun toString() =
        javaClass.simpleName + " - hash:" + hash + ", result:" + result + ",  date:" + time + ", fee:" + fee
}

data class current_acc(
    @SerializedName("final_balance")
    val final_balance: Long?)


// Data Model for the Response returned from API
data class TransactionResponse(
    val txs: List<txs>,
    val wallet: current_acc
)
