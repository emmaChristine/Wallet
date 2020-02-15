package com.demo.wallet.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.demo.wallet.R

import com.demo.wallet.data.dto.txs
import com.demo.wallet.ui.adapter.WalletAdapter
import com.demo.wallet.ui.utils.longToBTC
import com.demo.wallet.ui.viewmodels.TransactionViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.empty_transaction_list.*
import timber.log.Timber
import javax.inject.Inject

import kotlinx.android.synthetic.main.content_transactions_main.*
import kotlinx.android.synthetic.main.fragment_wallet.*


/**
 *  WalletFragment displays a list of transactions. At the moment the amount of transactions is restricted to 5.
 *  Ideally we would use a PaginatedAdapter in the RecyclerView and load pages based on user interaction.
 *
 */
class WalletFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var myViewModel: TransactionViewModel

    private lateinit var adapter: WalletAdapter

    private val mDisposable = CompositeDisposable()

    // observe transactions from API
    private val transactionObserver = Observer<List<txs>> { list ->

        list?.let {
            if (it.isNotEmpty()) {

                adapter.setData(it)
                myViewModel.insertAllTransactions(it)

                loading_indicator.visibility = View.GONE
                empty_transaction_list.visibility = View.GONE
            }
            else {
                // no data in repository either
                Timber.d("No transactions available.")

                loading_indicator.visibility = View.GONE
                empty_transaction_list.visibility = View.VISIBLE
            }
        }
    }

    private val balanceObserver = Observer<Long> { balance ->
        txt_balance.text = longToBTC(balance)
    }

    // region Fragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_wallet, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            myViewModel = ViewModelProvider(it, viewModelFactory)[TransactionViewModel::class.java]

            observeAdapter()
            loadTransactionList()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = WalletAdapter(activity!!)

        initView()
    }

    override fun onStop(){
        super.onStop()

        // clear all the subscriptions
        mDisposable.clear()
    }

    // endregion Fragment

    // region render

    private fun initView() {
        items_list.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        items_list.adapter = adapter
    }

    private fun loadTransactionList() {
        loading_indicator.visibility = View.VISIBLE

        myViewModel.fetchAllFromRemote()
    }
    // endregion render


    // region logic


    private fun observeAdapter() {
        myViewModel.myLiveData.observe(this, transactionObserver)
        myViewModel.balanceLiveData.observe(this, balanceObserver)
    }


    // endregion logic
}