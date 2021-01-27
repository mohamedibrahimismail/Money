package  com.example.money.network

import com.example.money.App
import com.example.money.model.GenericResponse
import com.example.money.model.budget_list_account.BudgetListAccount
import com.example.money.model.create_account.CreateAccountModel
import com.example.money.model.create_account.CreateAccountResponseModel
import com.example.money.model.main.BudgetListModel
import com.example.money.model.transaction_payee.TransactionModel
import io.reactivex.Observable


/**
 * AppRepository is the holder and repo for all network call apis
 * @author Mohamed Ibrahim
 */
class AppRepository : DataManager {
    override fun getBudgetList(): Observable<GenericResponse<BudgetListModel>> {
        return App.getService.getBudgetList()
    }

    override fun getBudgetAccounts(budget_id: String): Observable<GenericResponse<BudgetListAccount>> {
        return App.getService.getBudgetAccounts(budget_id)
    }

    override fun createAccount(
        budget_id: String,
        createAccount: CreateAccountModel
    ): Observable<GenericResponse<CreateAccountResponseModel>> {
        return App.getService.createAccount(budget_id,createAccount)
    }

    override fun getTransactionPayee(budget_id: String): Observable<GenericResponse<TransactionModel>> {
        return App.getService.getTransactionPayee(budget_id)
    }


}