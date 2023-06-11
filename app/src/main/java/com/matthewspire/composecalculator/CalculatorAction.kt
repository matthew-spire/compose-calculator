package com.matthewspire.composecalculator

sealed class CalculatorAction {
    // Data class for entering a number
    data class Number(val number: Int): CalculatorAction()

    // Object to clear the whole calculator
    object Clear: CalculatorAction()

    // Object to delete the last character entered
    object Delete: CalculatorAction()

    // Object to enter a decimal place
    object Decimal: CalculatorAction()

    // Object to perform the calculation (i.e., the = button)
    object Calculate: CalculatorAction()

    // Data class for performing an operation (i.e., +, -, etc.)
    data class Operation(val operation: CalculatorOperation): CalculatorAction()
}
