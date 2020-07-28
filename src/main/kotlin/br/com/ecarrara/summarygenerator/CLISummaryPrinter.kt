package br.com.ecarrara.summarygenerator

import br.com.ecarrara.utils.format
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
