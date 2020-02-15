package com.demo.wallet.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.wallet.ui.viewmodels.MainViewModel
import com.demo.wallet.ui.viewmodels.TransactionViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TransactionViewModel::class)
    abstract fun bindTransactionViewModel(viewmodel: TransactionViewModel): ViewModel

    @Binds
    abstract fun bindViewModelsFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
