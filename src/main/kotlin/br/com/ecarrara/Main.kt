package br.com.ecarrara

import br.com.ecarrara.core.networking.createRestService
import br.com.ecarrara.data.datasource.rest.BitriseRestService
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.ZoneId

fun main() {
    runBlocking {

        val fromDate = LocalDate.of(2020, 4, 1)
        val beforeDate = LocalDate.now().minusDays(1)

        val bitriseResponse = createRestService(BitriseRestService::class.java)
            .getBuilds(
                afterDate = fromDate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond(),
                beforeDate = beforeDate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond()
            )

        println(bitriseResponse)
    }
}