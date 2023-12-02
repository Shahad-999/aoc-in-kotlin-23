package day02
fun String.getMaximumRequiredCubit(): Int{
    this.substringAfter("Game ").apply {
        return this.substringAfter(": ").split(';').map {
            it.split(',').map {
                it.trim().toCube()
            }
        }.flatten().groupBy { it.color }.map { it.value.maxBy { it.numbers }.numbers }.reduce { acc, ele -> acc * ele }

    }
}

fun String.getGameValue(): Int{
    this.substringAfter("Game ").apply {
        this.substringAfter(": ").split(';').forEach {
            it.split(',').map {
                if(it.trim().toCube().isNotAllow()) return 0
            }
        }
        return this.substringBefore(":").trim().toInt()
    }
}
private fun Cube.isNotAllow(): Boolean {
    return when(this.color){
        CubeColor.RED -> this.numbers > 12
        CubeColor.GREEN -> this.numbers > 13
        CubeColor.BLUE -> this.numbers > 14
    }
}
fun String.toCube(): Cube{
    this.split(' ').apply {
        return Cube(this[0].toInt(),this[1].toCubeColor())
    }
}

fun String.toCubeColor(): CubeColor{
    return when(this.trim()){
        "red" -> CubeColor.RED
        "blue" -> CubeColor.BLUE
        else -> CubeColor.GREEN
    }
}
