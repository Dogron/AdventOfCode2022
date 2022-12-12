import kotlin.math.abs

private fun readInput(filename: String): List<String> {
    return object {}.javaClass.getResourceAsStream(filename)?.bufferedReader()?.readLines()!!
}

private fun main() {
    val filename = "Advent of Code (task 10).txt"
    val modInput = readInput(filename)
    println(modInput)

   // val resultPart1 = solutionPart1(modInput)
    // println(resultPart1)

    val resultPart2 = solutionPart2(modInput)
    resultPart2.forEach {
        println(it)
    }
}

fun solutionPart1(modInput: List<String>): Int {
    var cycle = 1
    var X = 1
    var total = 0

    modInput.forEach {
        if (it == "noop") {
            //loop starts

            //during loop
            if ((cycle - 20) % 40 == 0 && cycle <= 220) {
                total += cycle * X
            }

            //loop finishes
            cycle++

        }

        if (it.contains("addx")) {
            //loop starts

            //addx starts

            //during loop
            if ((cycle - 20) % 40 == 0 && cycle <= 220) {
                total += cycle * X
            }

            //loop finishes
            cycle++

            //loop starts

            //during loop
            if ((cycle - 20) % 40 == 0 && cycle <= 220) {
                total += cycle * X
            }

            //loop finishes
            cycle++

            X += it.split(" ").last().toInt()
        }

    }
    return total
}

fun solutionPart2(modInput: List<String>): List<MutableList<String>> {
    var cycle = 1
    var X = 1

    var completePicture = List(6) { MutableList(40) {"."} }

    modInput.forEach {
        if (it == "noop") {
            //loop starts

            //during loop
            if(abs((cycle-1)%40-X) <= 1)
                completePicture[(cycle-1)/40][(cycle-1)%40] = "#"

            //loop finishes
            cycle++

        }

        if (it.contains("addx")) {
            //loop starts
                //addx starts

            //during loop
            if(abs((cycle-1)%40-X) <= 1)
                completePicture[(cycle-1)/40][(cycle-1)%40] = "#"


            //loop finishes
            cycle++

            //loop starts

            //during loop
            if(abs((cycle-1)%40-X) <= 1)
                completePicture[(cycle-1)/40][((cycle-1)%40)] = "#"

            //loop finishes
            cycle++

            X += it.split(" ").last().toInt()
        }

    }
    return completePicture
}


//How to make this a function
