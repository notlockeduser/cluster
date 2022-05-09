package com.holovin.cluster.plagiarist.service

import de.jplag.JPlag
import de.jplag.JPlagResult
import de.jplag.options.JPlagOptions
import de.jplag.options.LanguageOption
import de.jplag.options.Verbosity
import de.jplag.reporting.Report
import de.jplag.strategy.ComparisonMode
import org.springframework.stereotype.Component
import java.io.File

@Component
class PlagiaristServiceImpl : PlagiaristService {

    override fun addFiles(files: List<File>) {
        TODO("Not yet implemented")
    }

    override fun checkLab(projectName: String): ResultOfCheck {
        val options = JPlagOptions(
            "C:\\Users\\Bogdan\\Desktop\\project-diplom\\cluster\\files", LanguageOption.JAVA
        )
//        options.baseCodeSubmissionName = "template"

        val jplag = JPlag(options)
        val result = jplag.run()

        val comparisons = result.comparisons

        val outputDir = File("/path/to/output")
        val report = Report(outputDir, options)

        report.writeResult(result)

        return ResultOfCheck("success projectName")
    }

    override fun checkFiles() {
        val (options, result) = resultOfRunPlagiaristChecker()
        printResult(result)

        val outputDir = File("C:\\Users\\Bogdan\\Desktop\\project-diplom\\cluster\\output")
        val report = Report(outputDir, options)
        report.writeResult(result)

    }

    private fun resultOfRunPlagiaristChecker(): Pair<JPlagOptions, JPlagResult> {
        val options = JPlagOptions(
            "C:\\Users\\Bogdan\\Desktop\\project-diplom\\cluster\\files",
            LanguageOption.JAVA
        )
        options.comparisonMode = ComparisonMode.NORMAL
        options.verbosity = Verbosity.LONG
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
}
