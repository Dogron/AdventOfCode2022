private fun readtxtfilePart1(filename: String): List<List<String>> {
    var resource = object {}.javaClass.getResourceAsStream(filename)?.bufferedReader()?.readLines()
    var modInput = resource!!.map {
        listOf(it.substring(0, it.length / 2), it.substring(it.length / 2))
    }
    return modInput

    //How to do it more efficiently? Split and 2D list
}

private fun readtxtfilePart2(filename: String): List<List<String>> {
    var resource = object {}.javaClass.getResourceAsStream(filename)!!.bufferedReader().readLines()
    var modInput = mutableListOf<List<String>>()

    var i = 3
    while (i <= resource.size) {
        modInput.add(resource.subList(i - 3, i))
        i += 3
    }
    return modInput

    //How to do it more efficiently? Split and 2D list
}
private fun main() {
    val modInputPart1 = readtxtfilePart1("Advent of Code (task 03).txt") //Modified Input for use in the program
    val modInputPart2 = readtxtfilePart2("Advent of Code (task 03).txt")

    val itemsPart1 = find_common_items_part1(modInputPart1)
    println(sum_char(itemsPart1))

    val itemsPart2 = findCommonItemsPart2(modInputPart2)
    println(sum_char(itemsPart2))
}
private fun sum_char(items: MutableList<Char>): Int {
    var sum = 0
    items.forEach {
        var i = it.code
        if (i > 96)
            sum += i - 96
        else
            sum += i - 38
    }
    return sum
}
private fun find_common_items_part1(modInput: List<List<String>>): MutableList<Char> {
    var i = 0
    var map0 = mutableMapOf<Char, Boolean>()
    var map1 = mutableMapOf<Char, Boolean>()

    println(modInput)

    var items = mutableListOf<Char>()
    modInput.forEach { list ->
        i = 0
        map0.clear()
        map1.clear()
        while (i < list[0].length) {
            var c = list[0][i]
            map0[c] = true

            if (map1[c] == true) {
                items.add(c)
                break
            }

            c = list[1][i]
            map1[c] = true

            if (map0[c] == true) {
                items.add(c)
                break
            }

            i++
        }
    }

    return items
}

private fun findCommonItemsPart2(modInput: List<List<String>>): MutableList<Char> {
    var i: Int
    var map0 = mutableMapOf<Char, Boolean>()
    var map1 = mutableMapOf<Char, Boolean>()
    var map2 = mutableMapOf<Char, Boolean>()

    println(modInput)

    var items = mutableListOf<Char>()
    modInput.forEach { list ->
        i = 0
        map0.clear()
        map1.clear()
        map2.clear()
        while (i < list[0].length || i < list[1].length || i < list[2].length) {

            var c: Char
            if (i < list[0].length) {
                var c = list[0][i]
                map0[c] = true

                if (map1[c] == true && map2[c] == true) {
                    items.add(c)
                    break
                }
            }

            if (i < list[1].length) {
                c = list[1][i]
                map1[c] = true

                if (map0[c] == true && map2[c] == true) {
                    items.add(c)
                    break
                }
            }

            if (i < list[2].length) {
                c = list[2][i]
                map2[c] = true

                if (map0[c] == true && map1[c] == true) {
                    items.add(c)
                    break
                }
            }

            i++
        }
    }

    return items
}