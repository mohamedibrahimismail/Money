package com.example.money.model.transaction_payee

data class Transaction(
    val account_id: String,
    val account_name: String,
    val amount: Int,
    val approved: Boolean,
    val category_id: String,
    val category_name: String,
    val cleared: String,
    val date: String,
    val deleted: Boolean,
    val flag_color: String,
    val id: String,
    val import_id: String,
    val matched_transaction_id: String,
    val memo: String,
    val payee_id: String,
    val payee_name: String,
    val subtransactions: List<Subtransaction>,
    val transfer_account_id: String,
    val transfer_transaction_id: String
)