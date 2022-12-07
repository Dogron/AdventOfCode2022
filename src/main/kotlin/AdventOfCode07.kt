private fun readInput(filename: String): List<String> {
    return object {}.javaClass.getResourceAsStream(filename)?.bufferedReader()?.readLines()!!.drop(1)
}

class TreeNode(){
    var children: MutableMap<String,TreeNode> = mutableMapOf()

    var name = "/"
    var size = 0
    var itIsFile = false
    var parent: TreeNode? = null
}

private fun main(){
    val filename = "Advent of Code (task 07).txt"
    val modInput = readInput(filename)

    println(modInput)
    val Tree = constructTree(modInput)
    calculateTree(Tree)

    val directorySizes = printSizes(Tree)
    println(directorySizes)

    //part1 solution:
    var total = 0
    directorySizes.forEach {
        if (it < 100000) {
            total+=it
        }
    }
    println("Solution part 1: $total")

    //part 2 solution
    total = directorySizes[0]
    val minimalToDelete = 30000000 - (70000000 - total)
    for (it in directorySizes.sorted()) {
        if(it>minimalToDelete) {
            println("Solution part 2: $it")
            break
        }
    }
}

private fun printSizes(root: TreeNode): List<Int> {
    println(root.name + ": " + root.size)
    val returnSizes = mutableListOf(root.size)

    root.children.values.forEach {
        if (!it.itIsFile)
            returnSizes.addAll(printSizes(it))
    }

    return returnSizes
}

private fun calculateTree(root: TreeNode): Int {
    var total = 0

    root.children.values.forEach {
        total += if (it.size == 0) {
            calculateTree(it)
        } else {
            it.size
        }
    }

    root.size = total
    return root.size
}

private fun constructTree(modInput: List<String>): TreeNode {
    var currentDirectory = TreeNode()

    var i = 0
    while (i<modInput.size) {
        var it = modInput[i]
        if (it.contains("ls")) {
            i++
            it = modInput[i]
            while (!it.contains("$")) {
                val newchild = TreeNode()
                if (it.contains("dir")) {
                    newchild.itIsFile = false
                    newchild.parent = currentDirectory
                    newchild.name = it.substringAfterLast(" ")
                    newchild.size = 0

                    currentDirectory.children[newchild.name] = newchild
                } else
                {
                    newchild.itIsFile = true
                    newchild.parent = currentDirectory
                    newchild.name = it.substringAfterLast(" ")
                    newchild.size = it.substringBefore(" ").toInt()

                    currentDirectory.children[newchild.name] = newchild
                }
                i++
                if (i==modInput.size) {
                    break
                }
                it = modInput[i]
            }
        }
        if (it.contains("cd")) {
            if (it.contains("/")) {
                while (currentDirectory.parent != null) {
                    currentDirectory = currentDirectory.parent!!
                }
            } else if (it.contains("..")) {
                currentDirectory = currentDirectory.parent!!
            } else {
                currentDirectory = currentDirectory.children[it.substringAfterLast(" ")]!!
            }
        }
        i++
    }

    while (currentDirectory.parent != null) {
        currentDirectory = currentDirectory.parent!!
    }

    return currentDirectory
}