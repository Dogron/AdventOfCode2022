private fun readtxtfilePart1(filename: String): List<List<List<Int>>> {
    var resource = object {}.javaClass.getResourceAsStream(filename)?.bufferedReader()?.readLines()
    var modInput = resource!!.map { it0 ->
        it0.split(",").map { it1 ->
            it1.split("-").map {
                it.toInt()
            }
        }
    }

    return modInput
}

private fun main() {
    val modInput = readtxtfilePart1("Advent of Code (task 04).txt")
    var answer = 0
    println(modInput)

    modInput.forEach {
        if ((it[0][1] <= it[1][1] && it[0][0] >= it[1][0]) || (it[1][1] <= it[0][1] && it[1][0] >= it[0][0])) {
            answer++
        }
    }

    println(answer)
    answer = 0

    modInput.forEach {
        if ((it[0][0] <= it[1][1] && it[0][1] >= it[1][0]) || (it[1][1] <= it[0][1] && it[1][0] >= it[0][0])) {
            answer++
        }
    }

    println(answer)
}

