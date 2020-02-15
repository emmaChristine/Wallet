package com.demo.wallet.ui.views


import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class WalletActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(WalletActivity::class.java)

    @Test
    fun walletActivityTest() {
    }
}
