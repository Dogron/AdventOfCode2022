private fun readTxtFile(filename: String): List<String> {
    return object {}.javaClass.getResourceAsStream(filename)!!.bufferedReader().readLines()
}
private fun main() {
    val modInput = readTxtFile("Advent of Code (task 01).txt")
    //How to make it automatically seek in the resource folder?
    var totalCalories = mutableListOf<Int>()
    var currentCalories = 0

    modInput.forEach {
        if (it == "") {
            totalCalories.add(currentCalories)
            currentCalories = 0
        } else {
            currentCalories += it.toInt()
        }
    }

    totalCalories.sort()

    println("First part: " + totalCalories[totalCalories.size - 1])
    println("Second part: " + (totalCalories[totalCalories.size - 1]+totalCalories[totalCalories.size - 2]+totalCalories[totalCalories.size - 3]))
}