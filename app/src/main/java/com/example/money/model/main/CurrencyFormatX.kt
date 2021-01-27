package com.example.money.model.main

data class CurrencyFormatX(
    val currency_symbol: String,
    val decimal_digits: Int,
    val decimal_separator: String,
    val display_symbol: Boolean,
    val example_format: String,
    val group_separator: String,
    val iso_code: String,
    val symbol_first: Boolean
)