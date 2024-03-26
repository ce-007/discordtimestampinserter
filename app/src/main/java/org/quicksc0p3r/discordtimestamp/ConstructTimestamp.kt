package org.quicksc0p3r.discordtimestamp

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant.Companion.fromEpochMilliseconds
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn

fun constructTimestamp(
    type: TimestampType,
    time: Pair<Int, Int>,
    dateEpochMillis: Long
): String {
    val tz = TimeZone.currentSystemDefault()
    val today = Clock.System.todayIn(tz)
    when (type) {
        TimestampType.SHORT_TIME, TimestampType.LONG_TIME -> {
            val timestamp = LocalDateTime(
                today.year,
                today.monthNumber,
                today.dayOfMonth,
                time.first, // hour
                time.second // minute
            ).toInstant(tz)
            val suffix = if (type == TimestampType.SHORT_TIME) "t" else "T"
            return "<t:${timestamp.epochSeconds}:${suffix}>"
        }
        else -> {
            val date = fromEpochMilliseconds(dateEpochMillis).toLocalDateTime(tz)
            val timestamp = LocalDateTime(
                date.year,
                date.monthNumber,
                date.dayOfMonth,
                time.first, // hour
                time.second // minute
            ).toInstant(tz)
            val suffix = when (type) {
                TimestampType.SHORT_DATE -> "d"
                TimestampType.LONG_DATE -> "D"
                TimestampType.SHORT_TIME_DATE -> "f"
                TimestampType.LONG_TIME_DATE -> "F"
                else -> "R"
            }
            return "<t:${timestamp.epochSeconds}:${suffix}>"
        }
    }
}