package de.floribe2000.warships_java.direct.api

import kotlinx.serialization.json.Json

val defaultJsonFormatter: Json = Json {
    prettyPrint = true
    isLenient = true
}