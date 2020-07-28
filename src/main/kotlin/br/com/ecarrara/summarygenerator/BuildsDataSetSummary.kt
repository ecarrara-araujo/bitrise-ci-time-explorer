package br.com.ecarrara.summarygenerator

import br.com.ecarrara.domain.entities.BuildInfo
import br.com.ecarrara.utils.format

data class BuildSummarizedData(
    val identifier: String,
    val averageBuildTimeInMinutes: Double,
    val averageWaitTimeInMinutes: Double,
    val numberOfBuilds: Int
) {
    override fun toString(): String {
        return "- $identifier: ${averageWaitTimeInMinutes.format(1)} mins waiting / " +
        "${averageBuildTimeInMinutes.format(1)} mins running / $numberOfBuilds builds"
    }
}

data class BuildsDataSetSummary(
    val buildsInfo: List<BuildInfo>
) {
    val buildsAnalysed: Int = buildsInfo.size
    val averageOfAllBuilds: Double = buildsInfo.map { it.buildTimeInMinutes }.average()
    val daysOfTheWeekSummarizedData: Collection<BuildSummarizedData> =
        buildsInfo.summarizeBy { it.startedAtDayOfTheWeek }
    val workflowsSummarizedData: Collection<BuildSummarizedData> = buildsInfo.summarizeBy { it.triggeredWorkflow }
    val numberOfFailedBuilds = buildsInfo.count { it.wasError }
    val failedBuildsRate: Double = numberOfFailedBuilds / buildsInfo.size.toDouble()
    val numberOfAbortedBuilds = buildsInfo.count { it.wasAborted }
    val abortedBuildsRate: Double = numberOfAbortedBuilds / buildsInfo.size.toDouble()
}

inline fun List<BuildInfo>.summarizeBy(
    crossinline keySelector: (BuildInfo) -> String
): Collection<BuildSummarizedData> {
    return asSequence()
        .filter { !it.wasAborted && !it.wasError }
        .groupBy(keySelector)
        .mapValues { (key, buildsInfo) ->
            BuildSummarizedData(
                identifier = key,
                averageBuildTimeInMinutes = buildsInfo.map { it.buildTimeInMinutes }.average(),
                averageWaitTimeInMinutes = buildsInfo.map { it.waitingTimeInMinutes }.average(),
                numberOfBuilds = buildsInfo.size
            )
        }
        .values
}
