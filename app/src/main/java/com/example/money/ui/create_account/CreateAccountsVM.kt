package com.example.money.ui.create_account

import androidx.lifecycle.MutableLiveData
import com.example.money.model.GenericResponse
import com.example.money.model.budget_list_account.BudgetListAccount
import com.example.money.model.create_account.CreateAccountModel
import com.example.money.model.create_account.CreateAccountResponseModel
import com.example.money.model.main.BudgetListModel
import com.example.money.network.AppRepository
import com.example.money.network.retrofit.CallbackWrapper
import com.example.money.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CreateAccountsVM(private val repository: AppRepository) : BaseViewModel() {


    var createAccount: MutableLiveData<CreateAccountResponseModel> = MutableLiveData()

    fun createAccount(id:String,createAccountModel: CreateAccountModel) {
        loading.value = true
        mCompositeDisposable.add(
            repository.createAccount(id,createAccountModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    CallbackWrapper<GenericResponse<CreateAccountResponseModel>>() {
                    override fun onSuccess(t: GenericResponse<CreateAccountResponseModel>) {
                        loading.value = false
                        createAccount.postValue(t.data)
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