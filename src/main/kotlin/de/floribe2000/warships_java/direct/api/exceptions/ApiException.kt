package de.floribe2000.warships_java.direct.api.exceptions

@Suppress("unused")
class ApiException(override val message: String, val code: Int) : Exception(message)