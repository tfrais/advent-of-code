package cz.frais.aoc.year2024.day05

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2024Day05 : AdventOfCodeDaySolution {

    fun isSortedCorrectly(pageOrderRules: List<Pair<Int, Int>>, pages: List<Int>): Boolean {
        val pageOrderMap = pages.withIndex()
            .associate { (index, page) -> page to index }

        pageOrderRules.forEach {
            if (pageOrderMap.contains(it.first) && pageOrderMap.contains(it.second)) {
                if (pageOrderMap[it.first]!! > pageOrderMap[it.second]!!) {
                    return false
                }
            }
        }
        return true
    }

    fun sort(pageOrderRules: List<Pair<Int, Int>>, pages: List<Int>): List<Int> {
        return pages.sortedWith { o1, o2 ->
            when {
                pageOrderRules.contains(Pair(o1, o2)) -> -1
                pageOrderRules.contains(Pair(o2, o1)) -> 1
                else -> o1.compareTo(o2)
            }
        }
    }

    private fun middleElement(page: List<Int>) = page[page.size / 2]

    override fun computePart1(input: String): Long {
        val parsedData = Parser.parse(input)
        return parsedData.pages
            .filter { isSortedCorrectly(parsedData.pageOrderRules, it) }
            .sumOf { middleElement(it) }.toLong()
    }

    override fun computePart2(input: String): Long {
        val parsedData = Parser.parse(input)
        return parsedData.pages
            .filter { !isSortedCorrectly(parsedData.pageOrderRules, it) }
            .map { sort(parsedData.pageOrderRules, it) }
            .sumOf { middleElement(it) }.toLong()
    }

}
