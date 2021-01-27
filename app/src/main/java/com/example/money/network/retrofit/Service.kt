package  com.example.money.network.retrofit

import com.example.money.model.GenericResponse
import com.example.money.model.budget_list_account.BudgetListAccount
import com.example.money.model.create_account.CreateAccountModel
import com.example.money.model.create_account.CreateAccountResponseModel
import com.example.money.model.main.BudgetListModel
import com.example.money.model.transaction_payee.TransactionModel
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Service is API call interface for retrofit
 * @author  Mohamed Ibrahim
 *
 */
interface Service {

    @GET("/v1/budgets")
    fun getBudgetList(): Observable<GenericResponse<BudgetListModel>>

    @GET("/v1/budgets/{budget_id}/accounts")
    fun getBudgetAccounts(@Path("budget_id") budget_id: String): Observable<GenericResponse<BudgetListAccount>>


    @POST("/v1/budgets/{budget_id}/accounts")
    fun createAccount(
        @Path("budget_id") budget_id: String,
        @Body createAccount: CreateAccountModel
    ): Observable<GenericResponse<CreateAccountResponseModel>>

    @GET("/v1/budgets/{budget_id}/transactions")
    fun getTransactionPayee(@Path("budget_id") budget_id: String): Observable<GenericResponse<TransactionModel>>


//    @GET("sirvices")
//    fun getServices(): Observable<GenericResponse<MutableList<ServiceModel>>>
//
//    @POST("complete_resevation")
//    fun completeReservation(
//        @Body completereServation: CompletereServation
//    ): Observable<GenericResponse<ServiceModel>>


}