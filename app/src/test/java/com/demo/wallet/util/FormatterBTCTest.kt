package com.demo.wallet.util

import com.demo.wallet.ui.utils.longToBTC
import com.demo.wallet.ui.utils.longToBTCWithStatus
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test

class FormatterBTCTest {

    private val btc:Long = 100000
    private val btcSent: Long = -98888

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun test_format()
    {
        val formatted = longToBTC(btc)
        assertEquals("\u20BF 0.00100000", formatted)
    }

    @Test
    fun test_formatWithStatusReceived()
    {
        val formatted = longToBTCWithStatus(btc)
        assertEquals("\u20BF 0.00100000 RECEIVED", formatted)
    }

    @Test
    fun test_formatWithStatusSent()
    {
        val formatted = longToBTCWithStatus(btcSent)
        assertEquals("\u20BF -0.00098888 SENT", formatted)
    }

    @Test
    fun test_formatInvalid() {
        val formatted = longToBTC(0)
        assertEquals("\u20BF 0.00000000", formatted)
    }
}