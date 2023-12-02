package day02

import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return  input.sumOf {
            it.getGameValue()
        }
    }
    fun part2(input: List<String>): Int {
        return input.sumOf{
            it.getMaximumRequiredCubit()
        }
    }
    
    
    val input = readInput("Day02", packageName = "day02")
    part1(input).println()
    part2(input).println()
    
}