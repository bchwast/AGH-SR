fun listCommands() {
    println("----- Commands -----")
    println("getServices")
    println("describeObject")
    println("getOperationResult")
    println("getPrimeTesterResult")
    println("getStreamPrimeNumbersResult")
    println("getListPrimeNumbersResult")
    println("exit")
    println("--------------------")
    println("Enter command: ")
}

fun matchCommand(command: String) {
    when (command) {
        "getServices" -> println(requestHandler?.getServices())
        "describeObject" -> {
            println("Enter object name: ")
            val obj = readlnOrNull()
            println(requestHandler?.describeObject(obj!!))
        }

        "getOperationResult" -> {
            println("Enter operation name (SUM, AVG, MULT, MIN, MAX): ")
            val operation = readlnOrNull()
//            if (!operations.contains(operation)) {
//                println("Unknown operation")
//                return
//            }
            println("Enter numbers: ")
            val input = readlnOrNull()
            val numbers = if (input!!.isNotEmpty()) input.split(" ").map { it.toLong() } else emptyList()
            println(requestHandler?.getOperationResult(operation!!, numbers!!))
        }

        "getPrimeTesterResult" -> {
            println("Enter number: ")
            val input = readlnOrNull()
            val number = if (input!!.isNotEmpty()) input.toLong() else null
            println(requestHandler?.getPrimeTesterResult(number))
        }

        "getStreamPrimeNumbersResult" -> {
            println("Enter max: ")
            val input = readlnOrNull()
            val max = if (input!!.isNotEmpty()) input.toLong() else null
            println(requestHandler?.getStreamPrimeNumbersResult(max))
        }

        "getListPrimeNumbersResult" -> {
            println("Enter max: ")
            val input = readlnOrNull()
            val max = if (input!!.isNotEmpty()) input.toLong() else null
            println(requestHandler?.getListPrimeNumbersResult(max))
        }

        else -> println("Unknown command")
    }
}

val operations = listOf("SUM", "AVG", "MULT", "MIN", "MAX")
var requestHandler: RequestHandler? = null

fun main(args: Array<String>) {
    requestHandler = RequestHandler(args[0])

    while (true) {
        listCommands()
        val command = readlnOrNull()
        if (command == "exit") {
            break
        }
        matchCommand(command!!)
    }
}
