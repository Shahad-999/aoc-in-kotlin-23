package day04

import println
import readInput
import kotlin.math.pow

fun main() {

    fun part1(input: List<String>): Double {
        return input.sumOf {
            it.getCard().findNumberOfWiningCards().calcPoints()
        }
    }

    fun part2(input: List<String>): Int {

        val cards = input.map { it.getCard() }
        return cards.mapIndexed { index, card ->
            val n = card.findNumberOfWiningCards()
            if (n + index < cards.size) {
                for (i in index + 1..n + index) {
                    cards[i].copies = cards[i].copies + (1 * cards[index].copies)
                }
            }
            card.copies
        }.sum()
    }

    val input = readInput("Day04_test", packageName = "day04")
    part1(input).println()
    part2(input).println()

}


data class Card(val wining: List<Int>, val own: List<Int>,var copies:Int = 1)
fun String.getCard(): Card {
    this.substringAfter(':').split('|').map {
        it.trim().split(Regex(" +")).map {num-> num.trim().toInt() }
    }.run {
        return Card(wining = this[0].sorted(), own = this[1].sorted())
    }
}

fun Card.findNumberOfWiningCards(): Int {
    if (own.isEmpty()) return 0
    var firstIndex = 0
    var num = 0
    for (winCardIndex in this.wining.indices) {
        if (firstIndex == own.size) break
        //less than
        if (wining[winCardIndex] < own[firstIndex]) {
            continue
        }
        //larger than
        for (ownCardsIndex in firstIndex..own.lastIndex) {
            if (wining[winCardIndex] > own[firstIndex]) {
                firstIndex++
                continue
            } else if (wining[winCardIndex] == own[firstIndex]) {
                firstIndex++
                num++
            } else {
                break
            }
        }
    }
    return num
}

fun Int.calcPoints(): Double {
    if (this == 0) return 0.0
    return 2.0.pow(this - 1.0)
}