package com.demo.wallet

import com.demo.wallet.data.dto.txs


object TestUtils {

    const val DEFAULT_TXS_HASH = "123abc"
    const val DEFAULT_TXS_DATE: Long =  908000
    const val DEFAULT_TXS_FEE: Long = 600
    const val DEFAULT_TXS_RES: Long = 908000

    val transactionDefault = txs(DEFAULT_TXS_HASH, DEFAULT_TXS_RES, DEFAULT_TXS_DATE, DEFAULT_TXS_FEE)
}