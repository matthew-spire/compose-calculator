package com.matthewspire.composecalculator

data class CalculatorState(
    val numberOne: String = "",
    val numberTwo: String = "",
    val operation: CalculatorOperation? = null
)
