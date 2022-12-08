import kotlin.math.max

private fun readInput(filename: String): List<List<Int>> {
    return object {}.javaClass.getResourceAsStream(filename)?.bufferedReader()?.readLines()!!.map {
        it.toList().map {
            it.digitToInt()
        }
    }
}

private fun main() {
    val filename = "Advent of Code (task 08).txt"
    val modInput = readInput(filename)

    println(modInput)
    val resultPart1 = countVisibleTrees(modInput)
    println(resultPart1)

    val resultPart2 = countScenicScore(modInput)
    println(resultPart2)
}


private fun countVisibleTrees(map: List<List<Int>>): Int {
    var total = map.size * map[0].size - ((map.size-2)*(map[0].size-2))

    for (v in 1..map.size-2 ) {
        for (h in 1..map[0].size-2) {
            val tree = map[v][h]

            //check horizontally
            if(map[v].take(h).all { it<tree } || map[v].drop(h+1).all { it<tree }) {
                total++
                continue
            }

            //check vertically
            var beforeCheck = true
            var afterCheck = true
            for (v1 in map.indices) {
                if (map[v1][h] >= tree && v1 < v) {
                    beforeCheck = false
                }
                if (map[v1][h] >= tree && v1 > v) {
                    afterCheck = false
                }
            }

            if(beforeCheck || afterCheck) {
                total++
                continue
            }
        }
    }

    return total
}

private fun countScenicScore(map: List<List<Int>>): Int {
    var bestTree = 0
    var currentTree = 1

    for (v in 1..map.size-2 ) {
        for (h in 1..map[0].size-2) {
            val tree = map[v][h]

            //calculate horizontally
                //calculate left
            var count = 1
            for (h1 in h-1 downTo  0) {
                if (map[v][h1] >= tree || h1 == 0) {
                    currentTree *= count
                    break
                }
                count++
            }
            count = 1

                //calculate right
            for (h1 in h+1 until map[v].size) {
                if (map[v][h1] >= tree || h1 == map[v].size - 1) {
                    currentTree *= count
                    break
                }
                count++
            }
            count = 1

            //calculate vertically
                //calculate top
            for (v1 in v-1 downTo 0) {
                if (map[v1][h] >= tree || v1 == 0) {
                    currentTree *= count
                    break
                }
                count++
            }
            count = 1
                //calculate bottom
            for (v1 in v+1 until map.size) {
                if (map[v1][h] >= tree || v1 == map.size-1) {
                    currentTree *= count
                    break
                }
                count++
            }

            bestTree = max(bestTree,currentTree)
            currentTree = 1
        }
    }


    return bestTree
}