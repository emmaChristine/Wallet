package com.demo.wallet.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import com.demo.wallet.data.dto.TransactionResponse
import com.demo.wallet.data.dto.txs
import com.demo.wallet.data.repository.WalletRepository
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Transaction view model.
 *
 * Ideally the fallback between remote and local data source is done at repository layer.
 */
class TransactionViewModel
@Inject constructor(val repository: WalletRepository) : BaseViewModel() {

    // my transactions
    var myLiveData = MutableLiveData<List<txs>>()
    var balanceLiveData = MutableLiveData<Long>()

    // region logic

    fun insertAllTransactions(list: List<txs>): Completable {
        // Actually there is no need to override DB each time, but only if there are changes.
        // In order to detect if changes are available, we could store a 'last_updated_time" field.
        return repository.insertAllTransactions(list)
    }

    fun fetchAllFromRemote() {

        scope.launch {
            val apiResponse: TransactionResponse? =
                repository.loadAllTransactions()

            if (apiResponse != null) {
                balanceLiveData.postValue(apiResponse.wallet.final_balance)
                myLiveData.postValue(apiResponse.txs)
                }
        }
    }

    fun fetchAllFromLocal() {

        repository.fetchAllFromLocal()
            .observeOn(Schedulers.computation())
            .subscribe { list ->
                list?.let {items ->
                    myLiveData.postValue(items)
                }
            }
    }

    // endregion logic

}


