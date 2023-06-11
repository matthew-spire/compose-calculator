package com.matthewspire.composecalculator

sealed class CalculatorOperation(val symbol: String) {
    // Object for addition
    object Add: CalculatorOperation("+")

    // Object for subtraction
    object Subtract: CalculatorOperation("-")

    // Object for multiplication
    object Multiply: CalculatorOperation("x")

    // Object for division
    object Divide: CalculatorOperation("/")
}
