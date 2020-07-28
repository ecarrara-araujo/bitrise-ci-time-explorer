package br.com.ecarrara.domain.entities

import br.com.ecarrara.utils.calculateDurationInMinutes
import br.com.ecarrara.utils.displayName
import java.time.LocalDateTime

data class BuildInfo(
    val buildNumber: Long,
    val triggeredWorkflow: String,
    val pullRequestId: Long,
    val pullRequestTargetBranch: String,
    val status: String,
    val abortReason: String,
    val triggeredAt: LocalDateTime?,
    val environmentPrepareFinishedAt: LocalDateTime?,
    val startedOnWorkerAt: LocalDateTime?,
    val finishedAt: LocalDateTime?,
    val startedAtDayOfTheWeek: String = triggeredAt?.dayOfWeek.displayName,
    val waitingTimeInMinutes: Long = calculateDurationInMinutes(triggeredAt, startedOnWorkerAt),
    val buildTimeInMinutes: Long = calculateDurationInMinutes(startedOnWorkerAt, finishedAt)
) {
    val wasAborted: Boolean
        get() = status == "aborted"

    val wasError: Boolean
        get() = status == "error"
}