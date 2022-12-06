private fun readSignal(filename: String): String {
    return object {}.javaClass.getResourceAsStream(filename)?.bufferedReader()?.readLine()!!
}

private fun main() {
    val filename = "Advent of Code (task 06).txt"
    val input = readSignal(filename)

    solution(input,4)
    solution(input,14)
}

private fun solution(input: String, uniqueCharactersInARow: Int) {
    for (i in 0..input.length - uniqueCharactersInARow) {
        val substring = input.substring(i, i + uniqueCharactersInARow)
        if (substring.toSet().size == substring.length) {
            println(i + uniqueCharactersInARow)
            break
        }
    }
}