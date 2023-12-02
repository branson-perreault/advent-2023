fun main() {

    fun isGameValid(line: String): Boolean {
        val game = line.substringAfter(": ")
        return game
            .split("; ")
            .all { pulls ->
                pulls.split(", ").all { cubecount ->
                    val (count, color) = cubecount.split(" ")
                    when (color) {
                        "red" -> count.toInt() <= 12
                        "green" -> count.toInt() <= 13
                        "blue" -> count.toInt() <= 14
                        else -> false
                    }
                }
            }
    }

    fun part1(input: List<String>): Int {
        return input
            .filter { line -> isGameValid(line) }
            .sumOf { line -> line.substringAfter("Game ").substringBefore(":").toInt() }
    }

    fun minCube(game: String, color: String): Int {
        return game
            .split(" ${color}")
            .dropLast(1)
            .map { seq -> seq.substringAfterLast(" ").toInt() }
            .max()
    }

    fun gamePower(line: String): Int {
        val game = line.substringAfter(": ")
        return minCube(game, "red") * minCube(game, "green") * minCube(game, "blue")
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { gamePower(it) }
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
