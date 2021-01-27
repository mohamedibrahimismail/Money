package com.example.money.model.main

data class Budget(
    val accounts: List<Account>,
    val currency_format: CurrencyFormat,
    val date_format: DateFormat,
    val first_month: String,
    val id: String,
    val last_modified_on: String,
    val last_month: String,
    val name: String
)