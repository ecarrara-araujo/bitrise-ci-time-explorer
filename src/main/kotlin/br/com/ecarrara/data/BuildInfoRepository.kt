package br.com.ecarrara.data

import br.com.ecarrara.core.networking.createRestService
import br.com.ecarrara.data.datasource.rest.BitriseResponse
import br.com.ecarrara.data.datasource.rest.BitriseRestService
import br.com.ecarrara.data.datasource.rest.BuildInfo
import java.time.LocalDate
import java.time.ZoneId

class BuildInfoRepository(
    private val bitriseRestService: BitriseRestService = createRestService(BitriseRestService::class.java)
) {

    suspend fun getPreviousAmountOfBuilds(numberOfBuildsToFetch: Long): List<BuildInfo> {
        val allFetchedBuilds = mutableListOf<BuildInfo>()

        val initialResponse = getBuilds(LocalDate.now())
        val neededNumberRequests = initialResponse.pagingInfo.totalItemCount?.let {
            it / numberOfBuildsToFetch
        } ?: 0
        allFetchedBuilds.addAll(initialResponse.builds)

        var currentCursor = initialResponse.pagingInfo.nextPageCursor
        for (count in 0..neededNumberRequests) {
            val nextResponse = getBuilds(nextCursor = currentCursor.orEmpty())
            allFetchedBuilds.addAll(nextResponse.builds)
            currentCursor = nextResponse.pagingInfo.nextPageCursor
            if (currentCursor.isNullOrBlank()) break
        }
        return allFetchedBuilds.take(numberOfBuildsToFetch.toInt())
    }

    private suspend fun getBuilds(beforeDate: LocalDate? = null, nextCursor: String = ""): BitriseResponse {
        val beforeDateEpoch = beforeDate?.atStartOfDay(ZoneId.systemDefault())?.toEpochSecond()
        return bitriseRestService.getBuilds(beforeDate = beforeDateEpoch, nextCursor = nextCursor)
    }
}