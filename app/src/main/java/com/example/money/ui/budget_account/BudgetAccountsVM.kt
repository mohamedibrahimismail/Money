package com.example.money.ui.budget_account

import androidx.lifecycle.MutableLiveData
import com.example.money.model.GenericResponse
import com.example.money.model.budget_list_account.Account
import com.example.money.model.budget_list_account.BudgetListAccount
import com.example.money.network.AppRepository
import com.example.money.network.retrofit.CallbackWrapper
import com.example.money.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BudgetAccountsVM(private val repository: AppRepository) : BaseViewModel() {


    var BudgetListAccount: MutableLiveData<List<Account>> = MutableLiveData()

    fun getBudgetAccount(id: String) {
        loading.value = true
        mCompositeDisposable.add(
            repository.getBudgetAccounts(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    CallbackWrapper<GenericResponse<BudgetListAccount>>() {
                    override fun onSuccess(t: GenericResponse<BudgetListAccount>) {
                        BudgetListAccount.postValue(filter(t.data!!.accounts))
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                        loading.value = false
                    }

                    override fun onFail(t: String?) {
                        loading.value = false
                    }

                    override fun onFail(t: Map<String, ArrayList<String>>) {
                        loading.value = false
                    }
                })
        )
    }

    fun filter(data: MutableList<Account>): List<Account> {
        var filteredList = mutableListOf<Account>()
        filteredList = data.filter { model -> !model.deleted }.toMutableList()
        filteredList.sortByDescending { it -> it.balance }
        return filteredList
    }

}