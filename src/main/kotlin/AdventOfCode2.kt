import java.io.File

private fun readTxtFile(filename: String): MutableList<List<String>> {
    val file: MutableList<List<String>> = mutableListOf(mutableListOf())
    File(filename).forEachLine { file.add(it.split(" ")) }
    return file.subList(1,file.size)

    //How to do it more efficiently? Split and 2D list
}

private fun main() {
    val file = readTxtFile("/Users/dogron/IdeaProjects/AvdentOfCode/src/main/resources/Advent of Code (task 2).txt")

    part1(file)
    part2(file)
}

private fun part1(file: MutableList<List<String>>) {
    var totalScore = 0
    val rulesVictory = mapOf("X" to "C", "Y" to "A", "Z" to "B")
    val corresponding = mapOf("A" to "X", "B" to "Y", "C" to "Z")
    val rulesPoints = mapOf("X" to 1, "Y" to 2, "Z" to 3)

    file.forEach {
        var currentScore = rulesPoints[it[1]]
        if (corresponding[it[0]] == it[1]) {
            currentScore = currentScore!! + 3
        } else if (rulesVictory[it[1]] == it[0]) {
            currentScore = currentScore!! + 6
        }

        totalScore += currentScore!!
    }

    println("Part 1 answer: $totalScore")
}

private fun part2(file: MutableList<List<String>>) {
    var totalScore = 0
    val rulesVictory = mapOf("A" to "B", "B" to "C", "C" to "A")
    val rulesDefeat = mapOf("B" to "A", "C" to "B", "A" to "C")
    val rulesPoints = mapOf("A" to 1, "B" to 2, "C" to 3)

    file.forEach {
        if (it[1] == "X") {
            totalScore += rulesPoints[rulesDefeat[it[0]]]!!
        }
        if (it[1] == "Y") {
            totalScore += (3 + rulesPoints[it[0]]!!)
        }
        if (it[1] == "Z") {
            totalScore += (6 + rulesPoints[rulesVictory[it[0]]]!!)
        }
    }

    println("Part 2 answer: $totalScore")
}