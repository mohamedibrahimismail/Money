package com.example.money.ui.base.viewmodel

import com.example.money.network.AppRepository
import com.example.money.ui.base.BaseViewModel

/**
 * CommonVM is view model for shared api calls in the app
 * like (Taxi options, Guide options , drivers , filters, currencies )
 * @author Mohamed Ibrahim
 */
class CommanVM(private val repository: AppRepository) : BaseViewModel() {


}