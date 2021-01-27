package com.example.money.model.budget_list_account

data class Account(
    val balance: Int,
    val cleared_balance: Int,
    val closed: Boolean,
    val deleted: Boolean,
    val id: String,
    val name: String,
    val note: String,
    val on_budget: Boolean,
    val transfer_payee_id: String,
    val type: String,
    val uncleared_balance: Int
)