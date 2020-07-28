package br.com.ecarrara.data.datasource.rest

import br.com.ecarrara.domain.EMPTY_BUILD_NUMBER
import br.com.ecarrara.domain.EMPTY_PULL_REQUEST_ID
import br.com.ecarrara.domain.NO_DATE_TIME_SET
import br.com.ecarrara.domain.entities.BuildInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun BuildInfoDto.toEntity() = BuildInfo(
    buildNumber = buildNumber ?: EMPTY_BUILD_NUMBER,
    triggeredWorkflow = triggeredWorkflow.orEmpty(),
    pullRequestId = pullRequestId ?: EMPTY_PULL_REQUEST_ID,
    pullRequestTargetBranch = pullRequestTargetBranch.orEmpty(),
    status = status.orEmpty(),
    abortReason = abortReason.orEmpty(),
    triggeredAt = parseBitriseDateTimeString(triggeredAt),
    environmentPrepareFinishedAt = parseBitriseDateTimeString(environmentPrepareFinishedAt),
    startedOnWorkerAt = parseBitriseDateTimeString(startedOnWorkerAt),
    finishedAt = parseBitriseDateTimeString(finishedAt)
)

fun parseBitriseDateTimeString(dateTime: String?): LocalDateTime? {
    return if (dateTime.isNullOrBlank()) {
        NO_DATE_TIME_SET
    } else {
        LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_ZONED_DATE_TIME)
    }
}
