import java.io.File

private fun readTxtFile(filename: String): List<String> {
    val file: MutableList<String> = mutableListOf()
    File(filename).forEachLine {file.add(it)}
    return file
}
private fun main() {
    val file = readTxtFile("/Users/dogron/IdeaProjects/AvdentOfCode/src/main/resources/Advent of Code (task 1).txt")
    //How to make it automatically seek in the resource folder?
    var totalCalories = mutableListOf<Int>()
    var currentCalories = 0

    file.forEach {
        if (it == "") {
            totalCalories.add(currentCalories)
            currentCalories = 0
        } else {
            currentCalories += it.toInt()
        }
    }

    totalCalories.sort()

    println("First part: " + totalCalories[totalCalories.size - 1])
    println("First part: " + (totalCalories[totalCalories.size - 1]+totalCalories[totalCalories.size - 2]+totalCalories[totalCalories.size - 3]))
}