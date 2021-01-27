package com.example.money.ui.payee_transactions

import androidx.lifecycle.MutableLiveData
import com.example.money.model.GenericResponse
import com.example.money.model.budget_list_account.Account
import com.example.money.model.budget_list_account.BudgetListAccount
import com.example.money.model.transaction_payee.Transaction
import com.example.money.model.transaction_payee.TransactionModel
import com.example.money.network.AppRepository
import com.example.money.network.retrofit.CallbackWrapper
import com.example.money.ui.base.BaseViewModel
import com.example.money.utils.AppConstants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PayeeTransactionVM(private val repository: AppRepository) : BaseViewModel() {


    var PayeeTransaction: MutableLiveData<List<Transaction>> = MutableLiveData()

    fun getPayeeTransaction( id:String) {
        loading.value = true
        mCompositeDisposable.add(
            repository.getTransactionPayee(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    CallbackWrapper<GenericResponse<TransactionModel>>() {
                    override fun onSuccess(t: GenericResponse<TransactionModel>) {
                        PayeeTransaction.postValue(t.data!!.transactions)
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


}