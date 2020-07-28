package br.com.ecarrara.csvgenerator

import br.com.ecarrara.data.datasource.rest.BuildInfo
import java.io.File
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

private const val CSV_FILE = "./build-info.csv"
private const val CSV_FILE_HEADERS = "build_number," +
        "status," +
        "pull_request_id," +
        "pull_request_target_branch," +
        "triggered_at," + "" +
        "started_on_worker_at," +
        "environment_prepare_finished_at," +
        "finished_at," +
        "waiting_time_minutes," +
        "build_time_minutes," +
        "start_at_weekday"

fun generateCSVFor(builds: List<BuildInfo>) {
    File(CSV_FILE).bufferedWriter().use { out ->
        out.write("$CSV_FILE_HEADERS\n")
        builds.forEach { out.write("${it.getCSVLine()}\n") }
    }
}

private fun BuildInfo.getCSVLine(): String {
    return "$buildNumber,$status," +
            "$pullRequestId,$pullRequestTargetBranch," +
            "$triggeredAt,$startedOnWorkerAt,$environmentPrepareFinishedAt," +
            "$finishedAt,$waitingTimeInMinutes,$buildTimeInMinutes," +
            startedAtDayOfTheWeek
}

private val BuildInfo.startedAtDayOfTheWeek: String
    get() {
        val triggeredAtDateTime = LocalDateTime.parse(triggeredAt, DateTimeFormatter.ISO_ZONED_DATE_TIME)
        return triggeredAtDateTime.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
    }

private val BuildInfo.waitingTimeInMinutes: Long
    get() {
        return if (triggeredAt != null && startedOnWorkerAt != null) {
            val triggeredAtDateTime = LocalDateTime.parse(triggeredAt, DateTimeFormatter.ISO_ZONED_DATE_TIME)
            val startedOnWorkerAtDateTime =
                LocalDateTime.parse(startedOnWorkerAt, DateTimeFormatter.ISO_ZONED_DATE_TIME)
            val duration = Duration.between(triggeredAtDateTime, startedOnWorkerAtDateTime)
            duration.toMinutes()
        } else {
            0
        }
    }

private val BuildInfo.buildTimeInMinutes: Long
    get() {
        return if (startedOnWorkerAt != null && finishedAt != null) {
            val startedOnWorkerAtDateTime =
                LocalDateTime.parse(startedOnWorkerAt, DateTimeFormatter.ISO_ZONED_DATE_TIME)
            val finishedAtDateTime = LocalDateTime.parse(finishedAt, DateTimeFormatter.ISO_ZONED_DATE_TIME)
            val duration = Duration.between(startedOnWorkerAtDateTime, finishedAtDateTime)
            duration.toMinutes()
        } else {
            0
        }
    }