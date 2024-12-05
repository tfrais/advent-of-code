package cz.frais.aoc.year2024.day05

import cz.frais.aoc.AdventOfCodeDaySolution

object Year2024Day05 : AdventOfCodeDaySolution {

    private fun computePageOrderMap(pages: List<Int>): Map<Int, Int> =
        pages.withIndex()
            .associate { (index, page) -> page to index }

    fun isSortedCorrectly(pageOrderRules: List<Pair<Int, Int>>, page: List<Int>): Boolean {
        val pageOrderMap = computePageOrderMap(page)
        pageOrderRules.forEach {
            if (pageOrderMap.contains(it.first) && pageOrderMap.contains(it.second)) {
                if (pageOrderMap[it.first]!! > pageOrderMap[it.second]!!) {
                    return false
                }
            }
        }
        return true
    }

    private fun middleElement(page: List<Int>) = page[page.size / 2]

    override fun computePart1(input: String): Long {
        val parsedData = Parser.parse(input)
        return parsedData.pages
            .filter { isSortedCorrectly(parsedData.pageOrderRules, it) }
            .sumOf { middleElement(it) }.toLong()
    }

    override fun computePart2(input: String): Long {
        TODO("Not yet implemented")
    }

}
