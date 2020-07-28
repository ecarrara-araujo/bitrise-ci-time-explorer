package br.com.ecarrara.summarygenerator

import br.com.ecarrara.utils.format
import br.com.ecarrara.utils.formatAsRate
import java.time.LocalDateTime

fun BuildsDataSetSummary.print() {
    val textReport = """
    +----------------------------------------------------------------------------+
    |                                                                            | 
    |                  Bitrise Data Extraction Summary Report                    | 
    |                                                                            |
    +----------------------------------------------------------------------------+
      Date Generated: ${LocalDateTime.now()}                                                        
      Number of Builds Analyzed: $buildsAnalysed builds                          
      Average Build Time: ${averageOfAllBuilds.format(1)} minutes
      Number of Failed Builds: $numberOfFailedBuilds builds
      Build Failure Rate: ${failedBuildsRate.formatAsRate()}
      Number of Aborted Builds: $numberOfAbortedBuilds builds
      Build Abortion Rate: ${abortedBuildsRate.formatAsRate()}
    +----------------------------------------------------------------------------+                                                                            
      Averages over Weekdays:
      ${daysOfTheWeekSummarizedData.joinToString("\n      ")}
    +----------------------------------------------------------------------------+
      Averages per Workflow:                                                     
      ${workflowsSummarizedData.joinToString("\n      ")}
      
      End of Report                                                              
    +----------------------------------------------------------------------------+
    """
    print(textReport)
}
