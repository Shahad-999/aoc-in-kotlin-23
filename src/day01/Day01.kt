package day01

import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { line->
            line.filter { it.isDigit() }.run {
                combineFirstAndLastAsNum()
            }
        }
    }
    fun part2(input: List<String>): Int {
        return input.sumOf {line->
            val digits = StringBuilder()
            line.forEachIndexed { index ,char ->
                digits.append(getFirstNumber(line,char,index))
            }
            digits.toString().combineFirstAndLastAsNum()
        }
    }
    
    
    val input = readInput("Day01", packageName = "day01")
    part1(input).println()
    part2(input).println()
    
}
fun getFirstNumber(text: String, firstDigit: Char,currentIndex:Int): String {
    if(firstDigit.isDigit()) {
        return "$firstDigit"
    }else{
        getNumberOrNull(text.substring(currentIndex))?.let {number->
            return "$number"
        }
    }
    return ""
}

val numbers = listOf("one", "two", "three", "four", "five", "six", "seven", "eight","nine" )
fun getNumberOrNull(text: String): Int?{
    numbers.forEachIndexed { index, num ->
        if(text.take(num.length).contains(num)) return  index+1
    }
    return null
}
fun String.combineFirstAndLastAsNum(): Int {
    return "${first()}${last()}".toInt()
}