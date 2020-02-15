package com.demo.wallet.ui.utils

import com.demo.wallet.AppConstants.SATOSHI_EXCHANGE_BTC


fun longToBTC(balance: Long?): String {

    balance?.let {
        val btc: Double = it /  SATOSHI_EXCHANGE_BTC

        return "\u20BF " + String.format("%.8f".format(btc))
    }

    return ""
}

fun longToBTCWithStatus(balance: Long?): String {

    balance?.let {
        val btc: Double = it /  SATOSHI_EXCHANGE_BTC

        return if (balance > 0)
            "\u20BF " + String.format("%.8f".format(btc)) + " RECEIVED "
        else
            "\u20BF " + String.format("%.8f".format(btc)) + " SENT "
    }

    return ""
}