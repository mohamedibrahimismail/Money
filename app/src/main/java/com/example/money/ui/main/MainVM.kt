package com.example.money.ui.main

import androidx.lifecycle.MutableLiveData
import com.example.money.model.GenericResponse
import com.example.money.model.main.BudgetListModel
import com.example.money.network.AppRepository
import com.example.money.network.retrofit.CallbackWrapper
import com.example.money.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainVM(private val repository: AppRepository) : BaseViewModel() {

    var BudgetsList: MutableLiveData<BudgetListModel> = MutableLiveData()

    fun getBudgetList() {
        loading.value = true
        mCompositeDisposable.add(
            repository.getBudgetList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :
                    CallbackWrapper<GenericResponse<BudgetListModel>>() {
                    override fun onSuccess(t: GenericResponse<BudgetListModel>) {
                        loading.value = false
                        BudgetsList.postValue(t.data)
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