package com.example.money.di

import com.example.money.network.AppRepository
import com.example.money.network.retrofit.LoginPref
import com.example.money.ui.base.viewmodel.CommanVM
import com.example.money.ui.budget_account.BudgetAccountsVM
import com.example.money.ui.create_account.CreateAccountsVM
import com.example.money.ui.main.MainVM
import com.example.money.ui.payee_transactions.PayeeTransactionVM
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Hero is the appModule for koin used to define view models && inject singleton class's objects
 * @author Mohamed Ibrahim
 */
val appModule = module {

    //repos
    factory { AppRepository() }
    factory { LoginPref(androidContext()) }


    //viewmodel
    viewModel { CommanVM(get()) }
    viewModel { MainVM(get()) }
    viewModel { BudgetAccountsVM(get()) }
    viewModel { CreateAccountsVM(get()) }
    viewModel { PayeeTransactionVM(get()) }
}