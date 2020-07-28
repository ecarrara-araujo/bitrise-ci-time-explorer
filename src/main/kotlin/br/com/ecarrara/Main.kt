package br.com.ecarrara

import br.com.ecarrara.csvgenerator.generateCSVFor
import br.com.ecarrara.data.BuildInfoRepository
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        BuildInfoRepository().also {
            generateCSVFor(it.getPreviousAmountOfBuilds(1_000))
        }
    }
}