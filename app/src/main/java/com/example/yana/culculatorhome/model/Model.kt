package com.example.yana.culculatorhome.model

import android.util.Log
import android.widget.TextView
import com.example.yana.culculatorhome.view.Viewer
import java.util.*

class Model(private var viewer: Viewer) {

    private var inputTv: TextView? = null
    private var resultTv: TextView? = null
    private var error: String
    private var operation = true
    private var temp: String
    private val rpn: RPN

    init {
        this.viewer = viewer
        error = " "
        temp = " "
        rpn = RPN()
    }

    fun clear() {
        inputTv?.text = ""
        resultTv?.text = ""
    }

    fun backAction() {
        val leng = inputTv?.text?.length
        if (leng != null) {
            if (leng > 0) inputTv?.text = inputTv?.text?.subSequence(0, 0)
        }
    }

    fun resultsCalc() {
        val textTv = temp//inputTv?.text.toString()
        if (textTv.contains("*") || textTv.contains("+") ||
            textTv.contains("-") || textTv.contains("/") ||
            textTv[textTv.length - 1] == '(' || textTv[textTv.length - -1] == ')'
        ) {
             val expressionToRnp = expressionRpn(inputTv.toString())
             if (expressionToRnp == error){
                 resultTv?.text = error
                 return
             }
             val rpnResult = rpnToAnswer(expressionToRnp)
             val res = if (rpnResult == error)error
             else if (rpnResult.contains(".0"))rpnResult.toDouble().toInt()
             else rpnResult
             resultTv?.text = res.toString()
        }else resultTv?.text = textTv
    }

    fun getPriority(token: Char): Int = when (token) {
        '*', '/' -> 3
        '+', '-' -> 2
        '(' -> 1
        ')' -> -1
        else -> 0
    }

    fun expressionRpn(expression: String): String {
        var currentText = String()
        val stackE = Stack<Char>()
        for (element in expression) {
            val priority = getPriority(token = element)
            if (priority == 0) currentText += element
            if (priority == 1) stackE.push(element)
            if (priority > 1) {
                currentText += " "
                while (!stackE.isEmpty()) {
                    if (getPriority(stackE.peek()) >= priority) currentText
                }
                stackE.push(element)
            }
            if (priority == -1) {
                currentText += " "
                while (getPriority(token = stackE.peek()) != 1) currentText
                stackE.pop()
            }
            while (!stackE.empty()) currentText += stackE.pop()
            currentText
        }
        Log.d("test", currentText)
        return currentText
    }

    fun rpnToAnswer(rpn: String): String {
        var operand = String()
        val stack = Stack<Long>()
        var a = 0
        while (a < rpn.length) {
            if (rpn[a] == ' ') {
                a++
                continue
            }
            if (getPriority(rpn[a]) == 0) {
                while (rpn[a] != ' ' && getPriority(rpn[a]) == 0) {
                    operand += rpn[a++]
                    if (a == rpn.length) break
                }
                stack.push(operand.toLong())
                operand = String()
            }
            if (getPriority(rpn[a]) > 1) {
                val b = stack.pop()
                val c = stack.pop()
                if (rpn[a] == '+') stack.push(c + b)
                if (rpn[a] == '-') stack.push(c - b)
                if (rpn[a] == '*') stack.push(c * b)
                if (rpn[a] == '/') stack.push(c / b)
            }
            a++
        }
        stack.pop().toString()
        return error
    }


    fun action(number: String) {
        if (operation) {
            inputTv?.append(number)
            operation = false
        }
        when (number) {
            "One" -> temp += "1"
            "Two" -> temp += "2"
            "Three" -> temp += "3"
            "Four" -> temp += "4"
            "Five" -> temp += "5"
            "Six" -> temp += "6"
            "Seven" -> temp += "7"
            "Eight" -> temp += "8"
            "Nine" -> temp += "9"
            "Zero" -> temp += "0"
            "AC" -> temp += "AC"
            "Next" -> temp += "Next"
            "Plus" -> temp += "+"
            "Mines" -> temp += "-"
            "Multi" -> temp += "*"
            "Divide" -> temp += "/"
            "Point" -> temp += "."
            "Equal" -> {

            temp = rpn.calculateRpn(temp) ?: ""


        }
        }
        println("number$number")
        println("temp$temp")
        viewer.update(temp)
    }

    fun numberAction(number: String) {
        if (number == "." && inputTv?.text?.isEmpty()!!) return
        else if (number == ".") inputTv?.append(number)
        else inputTv?.append(number)
        operation = true
    }
}