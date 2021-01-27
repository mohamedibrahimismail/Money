package com.example.money.model.transaction_payee

data class TransactionModel(
    val server_knowledge: Int,
    val transactions: List<Transaction>
)