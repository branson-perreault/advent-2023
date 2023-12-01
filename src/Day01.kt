fun main() {
    val digits = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            "${line.first { c: Char -> c.digitToIntOrNull() !== null }}${line.last { c: Char -> c.digitToIntOrNull() !== null }}".toInt()
        }
    }

    fun firstDigit(line: String): Int {
        val minDigitPos = line.indexOfFirst { c -> c.digitToIntOrNull() !== null }
        val minStringPos = digits.map { digit -> line.indexOf(digit) }.filter { index -> index >= 0 }.minOrNull() ?: line.length

        if (minDigitPos >= 0 && minDigitPos < minStringPos) {
            return line[minDigitPos].digitToInt()
        } else {
            val minString = digits.first { digit -> line.substring(minStringPos).startsWith(digit) }
            return digits.indexOf(minString) + 1
        }
    }

    fun lastDigit(line: String): Int {
        val maxDigitPos = line.indexOfLast { c -> c.digitToIntOrNull() !== null }
        val maxStringPos = digits.map { digit -> line.lastIndexOf(digit) }.filter { index -> index >= 0 }.maxOrNull() ?: -1

        if (maxDigitPos >= 0 && maxDigitPos > maxStringPos) {
            return line[maxDigitPos].digitToInt()
        } else {
            val maxString = digits.first { digit -> line.substring(maxStringPos).startsWith(digit) }
            return digits.indexOf(maxString) + 1
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { line ->
            "${firstDigit(line)}${lastDigit(line)}".toInt()
        }
    }

    var testInputA = readInput("Day01_test")
    println(part1(testInputA))
    check(part1(testInputA) == 142)

    var testInputB = readInput("Day01_testb")
    println(part2(testInputB))
    check(part2(testInputB) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
