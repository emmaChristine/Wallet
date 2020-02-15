package com.demo.wallet

import com.demo.wallet.data.dto.txs


object TestUtils {

    const val DEFAULT_TXS_HASH = 1
    const val DEFAULT_TXS_DATE =  "1.01.2020"
    const val DEFAULT_TXS_FEE = 6.0
    const val DEFAULT_TXS_RES = "111"

    val transactionDefault = txs(DEFAULT_TXS_HASH, DEFAULT_TXS_RES, DEFAULT_TXS_DATE, DEFAULT_TXS_FEE)
}