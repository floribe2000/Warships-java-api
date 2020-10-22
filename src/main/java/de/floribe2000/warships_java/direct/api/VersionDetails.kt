package de.floribe2000.warships_java.direct.api

data class VersionDetails(private val first: Int, private val second: Int, private val third: Int) {
    override fun toString(): String {
        return "$first.$second.$third"
    }
}