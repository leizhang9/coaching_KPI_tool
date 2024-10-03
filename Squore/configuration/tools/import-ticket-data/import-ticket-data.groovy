//package tools

import com.squoring.squore.groovy.dp.utils.DPUtils
import com.squoring.squore.groovy.dp.utils.AbstractDPEngine
import com.squoring.squore.groovy.dp.utils.CSVConverterUtils
import com.squoring.squore.groovy.toolkit.ToolkitEngine
import com.squoring.squore.groovy.toolkit.bean.Artifact
import com.squoring.squore.groovy.utils.GroovyScriptUtils
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import groovy.json.JsonSlurper
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.DataFormatter
import org.apache.poi.ss.usermodel.DateUtil
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.ss.util.CellReference
import org.apache.poi.ss.util.CellUtil
import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import groovy.io.FileType
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat
import java.util.Calendar

class ImportTICKETCase {
    static void main(String[] args) {
        println("Start of import of TICKET process")
       new ImportTICKETCaseEngine().generateResult(args[0], args[1]);
        println("End of import of TICKET process")
    }


}

class ImportTICKETCaseEngine {

    def data = [
        [Green: 12.92, Green_40: 5.168, Yellow: 15.2, Yellow_40: 6.08, Red: 24, Red_40: 9.6],
        [Green: 12.58, Green_40: 5.032, Yellow: 14.8, Yellow_40: 5.92, Red: 24, Red_40: 9.6],
        [Green: 12.24, Green_40: 4.896, Yellow: 14.4, Yellow_40: 5.76, Red: 24, Red_40: 9.6],
        [Green: 11.9,  Green_40: 4.76,  Yellow: 14.0, Yellow_40: 5.6,  Red: 24, Red_40: 9.6],
        [Green: 11.56, Green_40: 4.624, Yellow: 13.6, Yellow_40: 5.44, Red: 24, Red_40: 9.6],
        [Green: 11.22, Green_40: 4.488, Yellow: 13.2, Yellow_40: 5.28, Red: 24, Red_40: 9.6],
        [Green: 10.88, Green_40: 4.352, Yellow: 12.8, Yellow_40: 5.12, Red: 24, Red_40: 9.6],
        [Green: 10.54, Green_40: 4.216, Yellow: 12.4, Yellow_40: 4.96, Red: 24, Red_40: 9.6],
        [Green: 10.2,  Green_40: 4.08,  Yellow: 12.0, Yellow_40: 4.8,  Red: 24, Red_40: 9.6],
        [Green: 9.86,  Green_40: 3.944, Yellow: 11.6, Yellow_40: 4.64, Red: 24, Red_40: 9.6],
        [Green: 9.52,  Green_40: 3.808, Yellow: 11.2, Yellow_40: 4.48, Red: 24, Red_40: 9.6],
        [Green: 9.18,  Green_40: 3.672, Yellow: 10.8, Yellow_40: 4.32, Red: 24, Red_40: 9.6],
        [Green: 8.84,  Green_40: 3.536, Yellow: 10.4, Yellow_40: 4.16, Red: 24, Red_40: 9.6],
        [Green: 8.5,   Green_40: 3.4,   Yellow: 10.0, Yellow_40: 4.0,  Red: 24, Red_40: 9.6],
        [Green: 8.16,  Green_40: 3.264, Yellow: 9.6,  Yellow_40: 3.84, Red: 24, Red_40: 9.6],
        [Green: 7.82,  Green_40: 3.128, Yellow: 9.2,  Yellow_40: 3.68, Red: 24, Red_40: 9.6],
        [Green: 7.48,  Green_40: 2.992, Yellow: 8.8,  Yellow_40: 3.52, Red: 24, Red_40: 9.6],
        [Green: 7.14,  Green_40: 2.856, Yellow: 8.4,  Yellow_40: 3.36, Red: 24, Red_40: 9.6],
        [Green: 6.8,   Green_40: 2.72,  Yellow: 8.0,  Yellow_40: 3.2,  Red: 24, Red_40: 9.6],
        [Green: 6.46,  Green_40: 2.584, Yellow: 7.6,  Yellow_40: 3.04, Red: 24, Red_40: 9.6],
        [Green: 6.12,  Green_40: 2.448, Yellow: 7.2,  Yellow_40: 2.88, Red: 24, Red_40: 9.6],
    ]
    def calendar = Calendar.getInstance()
    def currentWeekNumber = calendar.get(Calendar.WEEK_OF_YEAR)
    def StartWeek = 24
    void generateResult(outFileDir,fileList) {
        ToolkitEngine toolkit = new ToolkitEngine()
        
        // def df = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        def df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        def df_bis = new SimpleDateFormat("M/d/yyyy");
        //def df_bis = new SimpleDateFormat("yyyy-MM-dd");
		//2024-05-03T07:00:04.000+0000
		
		// Retrieving the root node of the squore project (ie, the APPLICATION artifact)
		def rootArtifact = toolkit.getRootArtifact()
        
        fileList.split(";").each {
                def file = new File(it)
                if (file.exists()) {


					//////////////////////////////////////////////////////////
                    // CSV SAMPLE
                    /////////////////////////////////////////////////////////

					// Get rows, using colon separator
                    def  rows = CSVConverterUtils.getCsvRows(file.path, ",", true)

					//Create an intermediaite "Requirements" node below APPLICATION
					Artifact root_node = rootArtifact.makeArtifact("Tickets", "TICKET_FOLDER")
                    //rootArtifact.putMetric("CURRENT_TARGET_GREEN_TICKETS", data[currentWeekNumber-StartWeek]['Green'].toInteger())
                    //rootArtifact.putMetric("CURRENT_TARGET_YELLOW_TICKETS", data[currentWeekNumber-StartWeek]['Yellow'].toInteger())
                    //rootArtifact.putMetric("CURRENT_TARGET_RED_TICKETS", data[currentWeekNumber-StartWeek]['Red'].toInteger())
					rootArtifact.putMetric("CURRENT_TARGET_GREEN_TICKETS",data[currentWeekNumber-StartWeek]['Green'].toInteger())
                    rootArtifact.putMetric("CURRENT_TARGET_YELLOW_TICKETS",data[currentWeekNumber-StartWeek]['Yellow'].toInteger())
                    rootArtifact.putMetric("CURRENT_TARGET_RED_TICKETS",data[currentWeekNumber-StartWeek]['Red'].toInteger())
                    rootArtifact.putMetric("CURRENT_TARGET_GREEN40_TICKETS",data[currentWeekNumber-StartWeek]['Green_40'].toInteger())
                    rootArtifact.putMetric("CURRENT_TARGET_YELLOW40_TICKETS",data[currentWeekNumber-StartWeek]['Yellow_40'].toInteger())
                    rootArtifact.putMetric("CURRENT_TARGET_RED_TICKETS",data[currentWeekNumber-StartWeek]['Red'].toInteger())
                    rootArtifact.putMetric("CURRENT_TARGET_RED40_TICKETS",data[currentWeekNumber-StartWeek]['Red_40'].toInteger())
					// Parse each row of the CSV
                    rows.each { item ->
						// Extract row columns using their names from the CSV header


                        String itemID = item."Ticket Key"
						String itemName = item."Ticket Summary"


                        // Process the row
                        Artifact art_group = root_node.makeArtifact(item."Assignee".toString(), "TICKET_FOLDER")
                        Artifact art = art_group.makeArtifact(itemName, "TICKET")
                        //art_group.addKeys(itemID.toString())
                        art.addKeys(itemID.toString())
                        art.putInfo("ID", item."Ticket Key".toString()) 
						art.putInfo("URL", "https://jira.cc.bmwgroup.net/browse/" + item."Ticket Key".toString())
                        art.putInfo("SUMMARY", item."Ticket Summary".toString()) 
                        art.putInfo("ASSIGNEE", item."Assignee".toString()) 
                        art.putInfo("REPORTER", item."Reporter".toString()) 
                        art.putInfo("STATUS", item."Status".toString()) 
                        art.putInfo("PRIORITY", item."Priority".toString())
                        art.putInfo("CATEGORY", item."Category".toString())
                        art.putInfo("TICKET_QUALITY", item."Ticket Quality".toString())
                        art.putInfo("ESCAN", item."ESCAN Status".toString())
                        art.putInfo("PERFORMANCE", item."Performance Labels".toString()) 
						
						def date_value = item."Created Date".toString().split(/\+/)[0]
                        art.putDateMetric("CREATION_DATE", df.parse(date_value))
                        
						// 7 days + 1 hours
						art.putMetric("PROCESSING_TIME", getHours(item."Processing Time".toString())) 
                        art.putInfo("CURRENT_PHASE_STR", item."Current Phase".toString()) 
                        art.putInfo("PHASE_TRANSITION", item."Phase Transition".toString()) 
                        art.putMetric("PRE_ANALYSIS_BMW", getHours(item."Pre-analysis BMW".toString())) 
                        art.putMetric("PRE_ANALYSIS_VECTOR", getHours(item."Pre-analysis Vector".toString())) 
                        art.putMetric("ANALYSIS_VECTOR", getHours(item."Analysis Vector".toString())) 
                        art.putMetric("ANALYSIS_BMW", getHours(item."Analysis BMW".toString())) 
                        art.putInfo("SOLUTION_PHASE", item."Solution Phase".toString()) 
                        art.putInfo("NUMBER_OF_CYCLES", item."Number of cycles".toString()) 
                        art.putInfo("WARNING", item."Warning".toString()) 
                        art.putInfo("COMMENT", item."Comment".toString()) 
                        
						art.putDateMetric("LAST_UPDATE", df_bis.parse(item."Last Updated".toString())) 
                        if (item."Last Comment from Vector".toString() != "No comments by Vector members found")
                        {
                            art.putDateMetric("LAST_COMMENT_VECTOR", df_bis.parse(item."Last Comment from Vector".toString()))
                        }
                        //art.makeFinding("NEW_RULE_FOR_REQ").addMessages("Just an example")

                    }
                    ////////////////////////////////////////////////////////
		
                }
            }

        toolkit.generateFinalInputFile(outFileDir)
    }

	int getHours(String value) {
		def days = value.split(/ days \+ /)[0].toInteger()
		def hours = value.split(/ days \+ /)[1].split(/ /)[0].toInteger()
		return (days*8 + hours)
	}


}
