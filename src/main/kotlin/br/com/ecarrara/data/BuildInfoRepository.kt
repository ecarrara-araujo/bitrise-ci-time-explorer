package br.com.ecarrara.data

import br.com.ecarrara.core.networking.createRestService
import br.com.ecarrara.data.datasource.rest.BitriseResponseDto
import br.com.ecarrara.data.datasource.rest.BitriseRestService
import br.com.ecarrara.data.datasource.rest.BuildInfoDto
import java.time.LocalDate
import java.time.ZoneId

class BuildInfoRepository(
    private val bitriseRestService: BitriseRestService = createRestService(BitriseRestService::class.java)
) {

    suspend fun getPreviousAmountOfBuilds(numberOfBuildsToFetch: Long): List<BuildInfoDto> {
        val allFetchedBuilds = mutableListOf<BuildInfoDto>()

        val initialResponse = getBuilds(LocalDate.now())
        val neededNumberRequests = initialResponse.pagingInfoDto.totalItemCount?.let {
            it / numberOfBuildsToFetch
        } ?: 0
        allFetchedBuilds.addAll(initialResponse.buildDtos)

        var currentCursor = initialResponse.pagingInfoDto.nextPageCursor
        for (count in 0..neededNumberRequests) {
            val nextResponse = getBuilds(nextCursor = currentCursor.orEmpty())
            allFetchedBuilds.addAll(nextResponse.buildDtos)
            currentCursor = nextResponse.pagingInfoDto.nextPageCursor
            if (currentCursor.isNullOrBlank()) break
        }
        return allFetchedBuilds.take(numberOfBuildsToFetch.toInt())
    }

    private suspend fun getBuilds(beforeDate: LocalDate? = null, nextCursor: String = ""): BitriseResponseDto {
        val beforeDateEpoch = beforeDate?.atStartOfDay(ZoneId.systemDefault())?.toEpochSecond()
        return bitriseRestService.getBuilds(beforeDate = beforeDateEpoch, nextCursor = nextCursor)
    }
}