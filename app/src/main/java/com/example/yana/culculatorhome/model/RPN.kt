package com.example.yana.culculatorhome.model


class RPN {

        companion object {
            private val operations = arrayListOf("+", "-", "*", "/")
        }

        var elementStack = arrayListOf<Int>()

        fun calculateRpn(input: String): String {
            input.split(" ").map { unparsedElement -> parse(unparsedElement) }
            return elementStack.joinToString(" ")
        }

        private fun parse(unparsedElement: String) {
            if (isNumber(unparsedElement)) {
                elementStack.add(unparsedElement.toInt())
            } else if (isOperand(unparsedElement)) {
                elementStack.add(calculate(elementStack.pop(), elementStack.pop(), unparsedElement))
            }
        }

        private fun calculate(second: Int?, first: Int?, operation: String) = when (operation) {
            "+" -> first!! + second!!
            "-" -> first!! - second!!
            "*" -> first!! * second!!
            "/" -> first!! / second!!
            else -> throw Exception("Throw me")
        }

        private fun isNumber(unparsedElement: String): Boolean {
            try {
                unparsedElement.toInt()
            } catch (nfe: NumberFormatException) {
                return false
            }
            return true
        }

        private fun isOperand(unparsedElement: String): Boolean = operations.contains(unparsedElement)
    }

    private fun <E> ArrayList<E>.pop(): E? {
        if (this.isEmpty()) {
            return null
        } else {
            return removeAt(size - 1)
        }
    }

//
//        val stack: Stack<String> = Stack<String>()
//        var x: Int
//        var y: Int
//        var result = ""
//        var choice: String
//        var value = 0
//        val p = ""
//
//        for (i in input.indices){
//            val simbol = input[i].toString()
//
//            if (simbol != "+" && simbol != "-"
//                && simbol != "*" && simbol != "/") {
//                stack.push(simbol);
//                continue;
//            }
//            else {
//                choice = simbol;
//            }
//            when (choice) {
//                "+" ->{
//                    x = Integer.parseInt(stack.pop());
//                    y = Integer.parseInt(stack.pop());
//                    value = x + y;
//                    result = p + value;
//                    stack.push(result);
//                    break;
//                }
//
//                "-" ->{
//                    x = Integer.parseInt(stack.pop());
//                    y = Integer.parseInt(stack.pop());
//                    value = y - x;
//                    result = p + value;
//                    stack.push(result);
//                    break;
//                }
//                "*"->{
//                    x = Integer.parseInt(stack.pop());
//                    y = Integer.parseInt(stack.pop());
//                    value = x * y;
//                    result = p + value;
//                    stack.push(result);
//                    break;
//                }
//                "/"-> {
//                    x = Integer.parseInt(stack.pop());
//                    y = Integer.parseInt(stack.pop());
//                    value = y / x;
//                    result = p + value;
//                    stack.push(result);
//                    break;
//                }
//                else ->
//                    continue;
//            }
//    }
//        return Integer.parseInt(stack.pop())

