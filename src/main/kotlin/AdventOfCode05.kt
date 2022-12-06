private fun readCratesTable(filename: String): List<List<String>> {
    var resource = object {}.javaClass.getResourceAsStream(filename)?.bufferedReader()?.readLines()
    var i = 0
    resource!!.forEachIndexed { index, s ->
        if (s == "") {
            i = index
            //How to break?
        }
    }
    val amountofstacks = resource[i-1][resource[i-1].length-1].digitToInt()
    resource = resource.subList(0,i-1)

    val resource2 = resource.map {
        it.chunked(4)
    }

    var modInput = MutableList(size = amountofstacks) {
        MutableList(size = 0) {
            ""
        }
    }

    resource2.forEach {
            it.forEachIndexed { index, s ->
                val s0 = s.trim()
                if(s0.isNotEmpty()) {
                    modInput[index].add(s0.replace(("[^\\w\\d ]").toRegex(), ""))
                }
            }
    }

    modInput = modInput.map {
        it.reversed().toMutableList()
    }.toMutableList()

    return modInput
}

private fun readCommands(filename: String): List<List<Int>> {
    var resource = object {}.javaClass.getResourceAsStream(filename)?.bufferedReader()?.readLines()
    var i = 0
    resource!!.forEachIndexed { index, s ->
        if (s == "") {
            i = index
            //How to break?
        }
    }

    resource = resource.subList(i+1,resource.size)
    val resource2 = resource.map {
        it.split("\\s+".toRegex())
    }
    val modInput = MutableList(size = resource2.size) {
        mutableListOf<Int>()
    }
    resource2.forEachIndexed { index, strings ->
        strings.forEach {
            if(it.matches("-?\\d+(\\.\\d+)?".toRegex()) ) {
                modInput[index].add(it.toInt())
            }
        }
    }
    return modInput
}


private fun main(){
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
        var i = 0
        while (i < command[0]) {
            val item = modInput[command[1] - 1].last()
            modInput[command[1] - 1].removeLast()
            modInput[command[2] - 1].add(item)

            i++
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
        val items = modInput[command[1] - 1].subList(size-command[0], size)
        modInput[command[2]-1].addAll(items)
        repeat(command[0]) {modInput[command[1] - 1].removeLast()}
    }

    return modInput.joinToString("") { it.last() }
}