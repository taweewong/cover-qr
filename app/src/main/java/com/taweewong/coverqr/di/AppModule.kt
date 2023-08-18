package com.taweewong.coverqr.di

import com.taweewong.coverqr.db.PreferencesManager
import com.taweewong.coverqr.widget.CoverQRViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single { PreferencesManager(androidContext()) }

    viewModel { CoverQRViewModel(get()) }
}