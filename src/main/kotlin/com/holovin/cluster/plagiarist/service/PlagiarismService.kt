package com.holovin.cluster.plagiarist.service

import de.jplag.JPlag
import de.jplag.JPlagResult
import de.jplag.options.JPlagOptions
import de.jplag.options.LanguageOption
import de.jplag.reporting.Report
import org.springframework.stereotype.Component
import java.io.File

@Component
class PlagiarismService {

//     fun checkLabWithWeb(labName: String): ResultOfCheck {
//        val options = JPlagOptions(filesDb, LanguageOption.JAVA)
////        options.baseCodeSubmissionName = "template"
//
//        val jplag = JPlag(options)
//        val result = jplag.run()
//
//        val comparisons = result.comparisons
//
//        val outputDir = File("/path/to/output")
//        val report = Report(outputDir, options)
//
//        report.writeResult(result)
//
//        return ResultOfCheck("success projectName")
//    }

    fun checkLab(labName: String): List<String> {
        val (options, result) = resultOfRunPlagiaristChecker()
        printResult(result)

        return searchResultByName(result, labName)
    }

    private fun searchResultByName(result: JPlagResult, labName: String): List<String> {
        val comparisons = result.comparisons
        val result = mutableListOf<String>()
        for (comparison in comparisons) {
            val submissionNameA = comparison.firstSubmission.name
            val submissionNameB = comparison.secondSubmission.name

            if (submissionNameA == labName || submissionNameB == labName) {
                val percent = (comparison.similarity() * 10).toInt() / 10f

                result.add("$submissionNameA - $submissionNameB $percent")
                println("$submissionNameA - $submissionNameB $percent")
            }
        }
        return result
    }

    fun checkFiles() {
        val (options, result) = resultOfRunPlagiaristChecker()
        printResult(result)

        val outputDir = File(webOutput)
        val report = Report(outputDir, options)
        report.writeResult(result)
    }

    private fun resultOfRunPlagiaristChecker(): Pair<JPlagOptions, JPlagResult> {
        val options = JPlagOptions(filesDb, LanguageOption.JAVA)
//        options.comparisonMode = ComparisonMode.NORMAL
//        options.verbosity = Verbosity.LONG
        options.minimumTokenMatch = 1
        //        options.baseCodeSubmissionName = "template"

        val result = JPlag(options).run()
        return Pair(options, result)
    }

    private fun printResult(result: JPlagResult) {
        val comparisons = result.comparisons
        for (comparison in comparisons) {
            val submissionNameA = comparison.firstSubmission.name
            val submissionNameB = comparison.secondSubmission.name

            val percent = (comparison.similarity() * 10).toInt() / 10f

            println("$submissionNameA - $submissionNameB $percent")
        }
    }

    companion object {
        const val filesDb = "xFiles\\database_lab_files"
        const val webOutput = "xFiles\\web_output"
    }
}
