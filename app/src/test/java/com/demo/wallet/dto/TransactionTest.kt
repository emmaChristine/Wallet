package com.demo.wallet.dto

import com.demo.wallet.TestUtils.DEFAULT_TXS_DATE
import com.demo.wallet.TestUtils.DEFAULT_TXS_FEE
import com.demo.wallet.TestUtils.DEFAULT_TXS_HASH
import com.demo.wallet.TestUtils.DEFAULT_TXS_RES
import com.demo.wallet.TestUtils.transactionDefault
import com.demo.wallet.data.dto.txs
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TransactionTest {

    private lateinit var txs: txs

    @Before
    fun setUp() {
        txs = transactionDefault
    }

    @After
    fun tearDown() {}

    @Test
    fun test_default_values() {
        Assert.assertEquals(DEFAULT_TXS_HASH, txs.hash)
        Assert.assertEquals(DEFAULT_TXS_FEE, txs.fee)
        Assert.assertEquals(DEFAULT_TXS_RES, txs.result)
        Assert.assertEquals(DEFAULT_TXS_DATE, txs.time)
    }

    @Test
    fun test_toString() {
        Assert.assertNotNull(txs.toString())
    }
}