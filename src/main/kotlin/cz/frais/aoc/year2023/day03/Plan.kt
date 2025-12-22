package cz.frais.aoc.year2023.day03

class Plan(rawPlan: String) {

    companion object {
        private const val EMPTY_SYMBOL: Char = '.'
    }

    private var array: List<List<Char>>

    init {
        val array: MutableList<List<Char>> = mutableListOf()
        for (line in rawPlan.lines()) {
            array.add(line.toList())
            check(array.map { it.size }.distinct().size == 1) { "Inconsistent width of the plan" }
        }
        this.array = array.toList()
    }

    fun getHeight(): Int {
        return this.array.size
    }

    fun getWidth(): Int {
        return this.array[0].size
    }

    fun get(x: Int, y: Int): Char {
        return if (x in 0..<getWidth() && y in 0..<getHeight()) {
            array[y][x]
        } else {
            EMPTY_SYMBOL
        }
    }

    fun containsDigit(x: Int, y: Int): Boolean {
        return get(x, y).isDigit()
    }

    fun containsSymbol(x: Int, y: Int): Boolean {
        val cellValue = get(x, y)
        return !(cellValue.isDigit() || cellValue == EMPTY_SYMBOL)

    }

}
