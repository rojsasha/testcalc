package com.example.yana.culculatorhome.model

import java.util.*

class RPN {

    fun calculate(input: String): Int {

        val stack: Stack<String> = Stack<String>()
        var x: Int
        var y: Int
        var result = ""
        var choice: String
        var value = 0
        val p = ""

        for (i in input.indices){
            val simbol = input[i].toString()

            if (simbol != "+" && simbol != "-"
                && simbol != "*" && simbol != "/") {
                stack.push(simbol);
                continue;
            }
            else {
                choice = simbol;
            }
            when (choice) {
                "+" ->{
                    x = Integer.parseInt(stack.pop());
                    y = Integer.parseInt(stack.pop());
                    value = x + y;
                    result = p + value;
                    stack.push(result);
                    break;
                }

                "-" ->{
                    x = Integer.parseInt(stack.pop());
                    y = Integer.parseInt(stack.pop());
                    value = y - x;
                    result = p + value;
                    stack.push(result);
                    break;
                }
                "*"->{
                    x = Integer.parseInt(stack.pop());
                    y = Integer.parseInt(stack.pop());
                    value = x * y;
                    result = p + value;
                    stack.push(result);
                    break;
                }
                "/"-> {
                    x = Integer.parseInt(stack.pop());
                    y = Integer.parseInt(stack.pop());
                    value = y / x;
                    result = p + value;
                    stack.push(result);
                    break;
                }
                else ->
                    continue;
            }
    }
        return Integer.parseInt(stack.pop())
}}
