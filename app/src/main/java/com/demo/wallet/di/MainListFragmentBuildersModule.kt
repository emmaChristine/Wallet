package com.demo.wallet.di

import com.demo.wallet.ui.views.WalletFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainListFragmentBuildersModule {

    @ContributesAndroidInjector()
    abstract fun contributeWalletFragment(): WalletFragment

}

