package cz.frais.aoc.year2024.day13

@Suppress("MagicNumber")
enum class Button(val price: Int, val code: String) {

    BUTTON_A(3, "A"),
    BUTTON_B(1, "B");

    companion object {
        fun fromCode(code: String): Button {
            return Button.entries.find { it.code == code }
                ?: throw IllegalArgumentException("Unknown button code: $code")
        }
    }

}
