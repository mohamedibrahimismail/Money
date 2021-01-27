package com.example.money.model.main

data class DefaultBudget(
    val accounts: List<AccountX>,
    val currency_format: CurrencyFormatX,
    val date_format: DateFormatX,
    val first_month: String,
    val id: String,
    val last_modified_on: String,
    val last_month: String,
    val name: String
)