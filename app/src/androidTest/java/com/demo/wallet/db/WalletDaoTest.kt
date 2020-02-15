package com.demo.wallet.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.demo.wallet.TestUtils.DEFAULT_TXS_HASH
import com.demo.wallet.TestUtils.transactionDefault
import com.demo.wallet.data.dto.txs
import io.reactivex.functions.Predicate
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class WalletDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var dao: WalletDao

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    @Throws(Exception::class)
    fun initDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            // allowing main thread queries, just for testing
            .allowMainThreadQueries()
            .build()
        dao = db.walletDao()
    }

    @After
    @Throws(Exception::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAndGetTransaction() {
        // Given that we have a transaction in the data source
        val transactions: List<txs> = listOf(transactionDefault)

        db.walletDao().insertAllTransactions(transactions)
            .test()
            .assertComplete()


        // When subscribing to the emissions of transactions
        db.walletDao()
            .getAllTransactions()
            .test()
            // assertValue asserts that there was only one emission
            .assertValue(object: Predicate<List<txs>> {
                override fun test(t: List<txs>): Boolean {
                    assertEquals(1, t.size)
                    val transaction = t[0]

                    return transaction.hash.equals(DEFAULT_TXS_HASH)
                }

            })
    }
}