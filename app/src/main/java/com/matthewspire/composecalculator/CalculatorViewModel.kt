package com.matthewspire.composecalculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel: ViewModel() {
    var state by mutableStateOf(CalculatorState())
        // Let the UI see the state but not change it
        private set

    fun onAction(action: CalculatorAction) {
        when(action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Clear -> state = CalculatorState()
            is CalculatorAction.Delete -> performDeletion()
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Calculate -> performCalculation()
            is CalculatorAction.Operation -> enterOperation(action.operation)
        }
    }

    private fun enterNumber(number: Int) {
        if(state.operation == null) {
            if(state.numberOne.length >= MAX_NUM_LENGTH) {
                return
            }
            state = state.copy(
                numberOne = state.numberOne + number
            )
            return
        }
        if(state.numberTwo.length >= MAX_NUM_LENGTH) {
            return
        }
        state = state.copy(
            numberTwo = state.numberTwo + number
        )
    }

    companion object {
        private const val MAX_NUM_LENGTH = 8
    }

    private fun performDeletion() {
        when {
            state.numberTwo.isNotBlank() -> state = state.copy(
                numberTwo = state.numberTwo.dropLast(1)
            )
            state.operation != null -> state = state.copy(
                operation = null
            )
            state.numberOne.isNotBlank() -> state = state.copy(
                numberOne = state.numberOne.dropLast(1)
            )
        }
    }

    private fun enterDecimal() {
        if (state.operation == null && !state.numberOne.contains(".")
            && state.numberOne.isNotBlank()
        ) {
            state = state.copy(
                numberOne = state.numberOne + "."
            )
            return
        }
        if (!state.numberTwo.contains(".")
            && state.numberTwo.isNotBlank()
        ) {
            state = state.copy(
                numberTwo = state.numberTwo + "."
            )
        }
    }

    private fun performCalculation() {
        val numberOne = state.numberOne.toDoubleOrNull()
        val numberTwo = state.numberTwo.toDoubleOrNull()
        if(numberOne != null && numberTwo != null) {
            val result = when(state.operation) {
                is CalculatorOperation.Add -> numberOne + numberTwo
                is CalculatorOperation.Subtract -> numberOne - numberTwo
                is CalculatorOperation.Multiply -> numberOne * numberTwo
                is CalculatorOperation.Divide -> numberOne / numberTwo
                null -> return
            }
            state = state.copy(
                numberOne = result.toString().take(15),
                numberTwo = "",
                operation = null
            )
        }
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if(state.numberOne.isNotBlank()) {
            state = state.copy(operation = operation)
        }
    }
}