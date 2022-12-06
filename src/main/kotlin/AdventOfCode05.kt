private fun readCratesTable(filename: String): List<List<String>> {
    val resource = object {}.javaClass.getResourceAsStream(filename)?.bufferedReader()?.readLines()!!
    val separatorIndex = resource.indexOf("")
    val columns = resource[separatorIndex - 1].split(" ").last().toInt()
    val sliced = resource.take(separatorIndex - 1).map { it.chunked(4) }
    val modInput = MutableList(size = columns) {
        MutableList(size = 0) { "" }
    }

    sliced.forEach {
        it.forEachIndexed { index, s ->
            val cell = s.trim().removeSurrounding("[", "]")
            if (cell.isNotEmpty()) {
                modInput[index].add(cell)
            }
        }
    }

    return modInput.map { it.reversed() }
}

private fun readCommands(filename: String): List<List<Int>> {
    val resource = object {}.javaClass.getResourceAsStream(filename)?.bufferedReader()?.readLines()!!
    val separatorIndex = resource.indexOf("")

    return resource.drop(separatorIndex + 1).map { it.split(" ").mapNotNull { it.toIntOrNull() } }
}


private fun main() {
    val filename = "Advent of Code (task 05).txt"
    val modInput = readCratesTable(filename)
    val commands = readCommands(filename)


    val resultPart1 = part1solution(commands, modInput)
    val resultPart2 = part2solution(commands, modInput)

    println(resultPart1)
    println(resultPart2)
}

private fun part1solution(
    commands: List<List<Int>>,
    input: List<List<String>>
): String {
    val modInput = input.map { it.toMutableList() }
    commands.forEach { command ->
        repeat(command[0]) {
            val item = modInput[command[1] - 1].last()
            modInput[command[1] - 1].removeLast()
            modInput[command[2] - 1].add(item)
        }
    }

    return modInput.joinToString("") { it.last() }
}

private fun part2solution(
    commands: List<List<Int>>,
    input: List<List<String>>
): String {
    val modInput = input.map { it.toMutableList() }
    commands.forEach { command ->
        val size = modInput[command[1] - 1].size
        val items = modInput[command[1] - 1].subList(size - command[0], size)
        modInput[command[2] - 1].addAll(items)
        repeat(command[0]) { modInput[command[1] - 1].removeLast() }
    }

    return modInput.joinToString("") { it.last() }
}