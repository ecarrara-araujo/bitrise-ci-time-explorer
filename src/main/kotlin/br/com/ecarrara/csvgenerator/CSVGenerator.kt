package br.com.ecarrara.csvgenerator

import br.com.ecarrara.domain.entities.BuildInfo
import java.io.File

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
        "start_at_weekday," +
        "triggered_workflow"

fun generateCSVFor(buildDtos: List<BuildInfo>) {
    File(CSV_FILE).bufferedWriter().use { out ->
        out.write("$CSV_FILE_HEADERS\n")
        buildDtos.forEach { out.write("${it.getCSVLine()}\n") }
    }
}

private fun BuildInfo.getCSVLine(): String {
    return "$buildNumber,$status," +
            "$pullRequestId,$pullRequestTargetBranch," +
            "$triggeredAt,$startedOnWorkerAt,$environmentPrepareFinishedAt," +
            "$finishedAt,$waitingTimeInMinutes,$buildTimeInMinutes," +
            "$startedAtDayOfTheWeek,$triggeredWorkflow"
}
