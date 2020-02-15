package com.demo.wallet.di

import com.demo.wallet.ui.views.WalletActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [MainListFragmentBuildersModule::class])
    abstract fun contributeWalletActivity(): WalletActivity

}