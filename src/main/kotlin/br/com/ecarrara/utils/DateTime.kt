package br.com.ecarrara.utils

import java.time.DayOfWeek
import java.time.Duration
import java.time.format.TextStyle
import java.time.temporal.Temporal
import java.util.*

fun calculateDurationInMinutes(startInclusive: Temporal?, endExclusive: Temporal?): Long {
    return if (startInclusive == null || endExclusive == null) {
        0
    } else {
        Duration.between(startInclusive, endExclusive).toMinutes()
    }
}

val DayOfWeek?.displayName: String
    get() = this?.getDisplayName(TextStyle.FULL, Locale.ENGLISH).orEmpty()