import kotlin.math.max
import kotlin.math.min

fun main() {

    fun getBounds(line: String, number: String, currentY: Int, maxX: Int, maxY: Int): List<Int> {
        val lowerX = max(0, line.indexOf(number) - 1)
        val upperX = min(maxX, line.indexOf(number) + number.length)
        val lowerY = max(0, currentY - 1)
        val upperY = min(maxY, currentY + 1)
        return listOf(lowerX, upperX, lowerY, upperY)
    }

    fun part1(input: List<String>): Int {
        var sum = 0
        input.forEachIndexed { index, line ->
            var mutableLine = line
            val numbers = line.split(Regex("[\\D+]"))
            for (number in numbers) {
                if (number.toIntOrNull() !== null) {
                    val (lowerX, upperX, lowerY, upperY) = getBounds(
                        mutableLine,
                        number,
                        index,
                        mutableLine.length - 1,
                        input.size - 1
                    )

                    mutableLine = mutableLine.replaceFirst(number, ".".repeat(number.length))

                    var isAdjacentToSymbol = false
                    for (x in lowerX..upperX) {
                        for (y in lowerY..upperY) {
                            if (!input[y][x].toString().matches(Regex("\\d")) && input[y][x] != '.') {
                                isAdjacentToSymbol = true
                            }
                        }
                    }
                    if (isAdjacentToSymbol) {
                        sum += number.toInt()
                    }
                }
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var result = 0
        val gears = mutableMapOf<String, String>()
        input.forEachIndexed { index, line ->
            var mutableLine = line
            val numbers = line.split(Regex("[\\D+]"))
            for (number in numbers) {
                if (number.toIntOrNull() !== null) {
                    val (lowerX, upperX, lowerY, upperY) = getBounds(
                        mutableLine,
                        number,
                        index,
                        mutableLine.length - 1,
                        input.size - 1
                    )

                    mutableLine = mutableLine.replaceFirst(number, ".".repeat(number.length))

                    for (x in lowerX..upperX) {
                        for (y in lowerY..upperY) {
                            if (input[y][x] == '*') {
                                val key = "${x},${y}"
                                if (!gears.containsKey(key)) {
                                    gears[key] = number
                                } else if (gears[key] !== "used") {
                                    result += gears.getValue(key).toInt() * number.toInt()
                                    gears[key] = "used"
                                }
                            }
                        }
                    }

                }
            }
        }
        return result
    }

    val testInput = readInput("Day03_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
