package br.com.ecarrara.data.datasource.rest

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BitriseResponseDto(
    @Json(name = "data") val buildDtos: List<BuildInfoDto>,
    @Json(name = "paging") val pagingInfoDto: PagingInfoDto
)

@JsonClass(generateAdapter = true)
data class BuildInfoDto(
    @Json(name = "build_number") val buildNumber: Long?,
    @Json(name = "triggered_at") val triggeredAt: String?,
    @Json(name = "triggered_workflow") val triggeredWorkflow: String?,
    @Json(name = "started_on_worker_at") val startedOnWorkerAt: String?,
    @Json(name = "environment_prepare_finished_at") val environmentPrepareFinishedAt: String?,
    @Json(name = "finished_at") val finishedAt: String?,
    @Json(name = "status_text") val status: String?,
    @Json(name = "abort_reason") val abortReason: String?,
    @Json(name = "pull_request_id") val pullRequestId: Long?,
    @Json(name = "pull_request_target_branch") val pullRequestTargetBranch: String?
)

@JsonClass(generateAdapter = true)
data class PagingInfoDto(
    @Json(name = "total_item_count") val totalItemCount: Long?,
    @Json(name = "page_item_limit") val pageItemLimit: Int?,
    @Json(name = "next") val nextPageCursor: String?
)