package cz.frais.aoc.year2024.day05

object Parser {

    private const val PAGE_ORDER_SEPARATOR = "|"
    private const val PAGES_SEPARATOR = ","

    fun parse(input: String): ParsedData {
        val pageOrderingRules = mutableListOf<Pair<Int, Int>>()
        val pages = mutableListOf<List<Int>>()

        input.split("\n")
            .filter { it.isNotBlank() }
            .forEach {
                if (it.contains(PAGE_ORDER_SEPARATOR)) {
                    pageOrderingRules.add(
                        it.split(PAGE_ORDER_SEPARATOR)
                            .map { parts -> parts.trim().toInt() }
                            .let { (a, b) -> Pair(a, b) }
                    )
                } else {
                    pages.add(
                        it.split(PAGES_SEPARATOR)
                            .map { parts -> parts.trim().toInt() }
                    )
                }
            }

        return ParsedData(pageOrderingRules.toList(), pages.toList())
    }

}
