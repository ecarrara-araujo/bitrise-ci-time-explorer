package br.com.ecarrara

import br.com.ecarrara.csvgenerator.generateCSVFor
import br.com.ecarrara.data.BuildInfoRepository
import br.com.ecarrara.summarygenerator.BuildsDataSetSummary
import br.com.ecarrara.summarygenerator.print
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        BuildInfoRepository().getPreviousAmountOfBuilds(1_000).also {
            BuildsDataSetSummary(it).print()
            generateCSVFor(it)
        }
    }
}
