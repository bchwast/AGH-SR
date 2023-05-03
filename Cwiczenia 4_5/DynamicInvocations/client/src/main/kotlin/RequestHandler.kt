class RequestHandler(private val server: String) {

    private val curl = "./grpcurl.exe"
    private val plainText = "-plaintext"

    fun getServices(): String {
        val proc = ProcessBuilder(curl, plainText, server, "describe")
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)
            .start()

        proc.waitFor()
        return proc.inputStream.bufferedReader().readText()
    }

    fun describeObject(obj: String): String {
        val proc = ProcessBuilder(curl, plainText, server, "describe", obj)
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)
            .start()

        proc.waitFor()
        return proc.inputStream.bufferedReader().readText()
    }

    fun getOperationResult(operation: String, numbers: List<Long>): String {
        val args = "{\\\"opType\\\": \\\"$operation\\\", \\\"args\\\": $numbers}"
        val proc = ProcessBuilder(curl, plainText, "-d", args, server, "calculator.CalculatorService/Operation")
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.INHERIT)
            .start()

        proc.waitFor()
        return proc.inputStream.bufferedReader().readText()
    }

    fun getPrimeTesterResult(number: Long): String {
        val args = "{\\\"number\\\": $number}"
        val proc = ProcessBuilder(curl, plainText, "-d", args, server, "calculator.CalculatorService/PrimeTester")
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.INHERIT)
            .start()

        proc.waitFor()
        return proc.inputStream.bufferedReader().readText()
    }

    fun getStreamPrimeNumbersResult(max: Long): String {
        val args = "{\\\"max\\\": $max}"
        val proc = ProcessBuilder(curl, plainText, "-d", args, server, "calculator.PrimesService/StreamPrimeNumbers")
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.INHERIT)
            .start()

        proc.waitFor()
        return proc.inputStream.bufferedReader().readText()
    }

    fun getListPrimeNumbersResult(max: Long): String {
        val args = "{\\\"max\\\": $max}"
        val proc = ProcessBuilder(curl, plainText, "-d", args, server, "calculator.PrimesService/ListPrimeNumbers")
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.INHERIT)
            .start()

        proc.waitFor()
        return proc.inputStream.bufferedReader().readText()
    }
}
