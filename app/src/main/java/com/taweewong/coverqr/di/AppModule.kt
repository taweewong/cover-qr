package com.taweewong.coverqr.di

import com.taweewong.coverqr.widget.CoverQRViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    viewModel { CoverQRViewModel() }
}