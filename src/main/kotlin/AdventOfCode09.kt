private fun readInput(filename: String): List<List<String>> {
    return object {}.javaClass.getResourceAsStream(filename)?.bufferedReader()?.readLines()!!.map {
        it.split(" ")
    }
}

private fun main() {
    val filename = "Advent of Code (task 09).txt"
    val modInput = readInput(filename)
    part1solution(modInput)
    part2solution(modInput)
}

private fun part1solution(modInput: List<List<String>>) {
    var rope : MutableList<MutableList<Int>> = mutableListOf(mutableListOf(0,0),mutableListOf(0,0))

    var uniqueTailPositions = mutableSetOf<List<Int>>()

    modInput.forEach { command ->
        var axis = 0
        var increment = 1

        when(command[0]) {
            "R"-> {
                axis = 0
                increment = 1
            }
            "D"-> {
                axis = 1
                increment = -1
            }
            "L"-> {
                axis = 0
                increment = -1
            }
            "U"-> {
                axis = 1
                increment = 1
            }
        }

        repeat(command[1].toInt()) {
            rope[0][axis] += increment
            for (i in 1 until rope.size) {
                val move = move(rope[i], rope[i - 1])
                rope[i][0] += move[0]
                rope[i][1] += move[1]
            }
            uniqueTailPositions.add(listOf(rope[rope.size - 1][0], rope[rope.size - 1][1]))
        }
    }

    println(uniqueTailPositions)
    println(uniqueTailPositions.size)
}

private fun part2solution(modInput: List<List<String>>) {
    var rope : MutableList<MutableList<Int>> = mutableListOf(mutableListOf(0,0),mutableListOf(0,0),mutableListOf(0,0),mutableListOf(0,0),mutableListOf(0,0),mutableListOf(0,0),mutableListOf(0,0),mutableListOf(0,0),mutableListOf(0,0),mutableListOf(0,0))

    var uniqueTailPositions = mutableSetOf<List<Int>>()

    modInput.forEach { command ->
        var axis = 0
        var increment = 1

        when(command[0]) {
            "R"-> {
                axis = 0
                increment = 1
            }
            "D"-> {
                axis = 1
                increment = -1
            }
            "L"-> {
                axis = 0
                increment = -1
            }
            "U"-> {
                axis = 1
                increment = 1
            }
        }

        repeat(command[1].toInt()) {
            rope[0][axis] += increment
            for (i in 1 until rope.size) {
                val move = move(rope[i], rope[i - 1])
                rope[i][0] += move[0]
                rope[i][1] += move[1]
            }
            uniqueTailPositions.add(listOf(rope[rope.size - 1][0], rope[rope.size - 1][1]))
        }
    }

    println(uniqueTailPositions)
    println(uniqueTailPositions.size)
}

private fun move(tail: List<Int>,head: List<Int>): List<Int> {
    val dx = head[0]-tail[0]
    val dy = head[1]-tail[1]

    if(dx == 2 && dy == 0)
        return listOf(1,0)
    if(dx == 2 && dy == -1)
        return listOf(1,-1)
    if (dx == 2 && dy == -2)
        return listOf(1,-1)
    if(dx == 1 && dy == -2)
        return listOf(1,-1)
    if(dx == 0 && dy == -2)
        return listOf(0,-1)
    if(dx == -1 && dy == -2)
        return listOf(-1,-1)
    if (dx == -2 && dy == -2)
        return listOf(-1,-1)
    if(dx == -2 && dy == -1)
        return listOf(-1,-1)
    if(dx == -2 && dy == 0)
        return listOf(-1,0)
    if(dx == -2 && dy == 1)
        return listOf(-1,1)
    if (dx == -2 && dy == 2)
        return listOf(-1,1)
    if(dx == -1 && dy == 2)
        return listOf(-1,1)
    if(dx == 0 && dy == 2)
        return listOf(0,1)
    if(dx == 1 && dy == 2)
        return listOf(1,1)
    if (dx == 2 && dy == 2)
        return listOf(1,1)
    if(dx == 2 && dy == 1)
        return listOf(1,1)

    return listOf(0,0)
}