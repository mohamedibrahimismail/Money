package com.example.money.model.budget_list_account

data class BudgetListAccount(
    val accounts: MutableList<Account>,
    val server_knowledge: Int
)