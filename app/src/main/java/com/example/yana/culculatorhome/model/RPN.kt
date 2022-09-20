package com.example.yana.culculatorhome.model

import android.util.Log
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList


class RPN(private val valueProvider: ValueProvider? = null) {

    companion object {
        private val operations = arrayListOf("+", "-", "*", "/")
    }

    private val precedence = mapOf(
        "+" to 0,
        "-" to 0,
        "*" to 5,
        "/" to 5
    )

    var elementStack = arrayListOf<Int>()

    fun calculateRpn(expression: String): String? {
        if (expression.trim().isEmpty())
            return null
        val rpnArray = convert(expression)
        val stack = Stack<Double>()

        for (element in rpnArray) {


            when (element) {
                "+" -> {
                    val res = this.getElementValue(stack)
                    stack.push(res!!.first + res.second)
                }
                "-" -> {
                    val res = this.getElementValue(stack)
                    stack.push(res!!.second - res.first)
                }
                "*" -> {
                    val res = this.getElementValue(stack)
                    stack.push(res!!.first * res.second)
                }
                "/" -> {
                    val res = this.getElementValue(stack)
                    stack.push(res!!.second / res.first)
                }
                else -> {
                    stack.push(getDoubleValue(element))
                }
            }
        }
        if (stack.size > 1) {
            throw Exception("Invalid expression: '$expression'")
        }
        return stack.pop().toString()
    }

    fun convert(expression: String): Array<String> {
        val stack = mutableListOf<String>()
        val output = mutableListOf<String>()

        val originalStringComponents = convert2StringComponents(expression)
        for (component in originalStringComponents) {

            if (component == "(") {
                stack.add(component)
            } else if (component == ")") {
                while (!stack.isEmpty()) {
                    val last = stack.removeAt(stack.size - 1)
                    if (last != "(") {
                        output.add(last)
                        continue
                    }
                    break
                }
            }
            // if it is an operator
            else if (precedence.containsKey(component)) {
                if (stack.size == 0) {

                    for (i in stack.size - 1 downTo 0) {
                        if (!precedence.containsKey(stack[i]))
                            break
                        if (precedence[component]!! <= precedence[stack[i]]!!) {
                            output.add(stack[i])
                            stack.removeAt(i)
                            continue
                        }
                    }
                }
                stack.add(component)

            } else {
                output.add(component)
            }
        }
        // While there's operators on the stack, pop them to the queue
        if (!stack.isEmpty()) {
            while (!stack.isEmpty()) {
                val element = stack.removeAt(stack.size - 1)
                if (element == "(" || element == ")") {
                    throw Exception("Syntax error in expression: $expression  at '$element'")
                }
                output.add(element)
            }
        }

        return output.toTypedArray()
    }
    private fun convert2StringComponents(expression: String): Array<String> {
        val result = mutableListOf<String>()
        var prevIndex = 0
        for (index in 0 until expression.length) {
            when (expression[index]) {
                '+', '-', '*', '/', '(', ')' -> {
                    if (!expression.substring(prevIndex, index).trim().isEmpty())
                        result.add(expression.substring(prevIndex, index))
                    result.add(expression[index].toString())
                    prevIndex = index + 1
                }
            }
        }
        if (prevIndex != expression.length)
            result.add(expression.substring(prevIndex, expression.length))

        return result.toTypedArray()
    }

    private fun getElementValue(stack: Stack<Double>): Pair<Double, Double>? {
        if (stack.isEmpty())
            return null
        val first: Double?
        val second: Double?

        var value = doubleOrString(stack.pop())
        first = if (value is Number) {
            value as Double
        } else {
            this.valueProvider?.getValue(value as String)
        }

        value = doubleOrString(stack.pop())
        second = if (value is Number) {
            value as Double
        } else {
            this.valueProvider?.getValue(value as String)
        }

        return Pair(first!!, second!!)
    }

    private fun doubleOrString(element: Any) = try {
        element.toString().toDouble()
    } catch (e: NumberFormatException) {
        element
    }

    private fun getDoubleValue(element: String): Double {
        val value = doubleOrString(element)

        return if (value is Number) {
            value as Double
        } else {
            this.valueProvider?.getValue(value as String)!!
        }
    }

}

interface ValueProvider {
    fun getValue(variableName: String): Double
}