package com.holovin.cluster.test.service

import org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns
import org.junit.platform.engine.discovery.DiscoverySelectors.selectClasspathRoots
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder
import org.junit.platform.launcher.core.LauncherFactory
import org.junit.platform.launcher.listeners.SummaryGeneratingListener
import org.junit.platform.launcher.listeners.TestExecutionSummary
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.file.Path


@Component
class TestService {

    fun compileSrc(labFolder: String, labName: String): String {
        val labPath =
            "C:\\Users\\Bogdan\\Desktop\\project-diplom\\cluster\\xFiles\\database_lab_files\\id-651_sub431_lab2\\id-651_sub431_lab2_Wru_TiQaUNWftniOAotnzWvkQt_dwprLNbgNvZUMpqAXLJbjE"
        val source = "cd $labPath"
        val commandCompilationSrc = "mvn compile"
        val cmd = "cmd /c start cmd.exe /K \"$source & $commandCompilationSrc & EXIT\""

        var res = ""
        val exe: Process = Runtime.getRuntime().exec(cmd)
        try {
            exe.waitFor()
            val bin = BufferedReader(InputStreamReader(exe.inputStream))
            val berr = BufferedReader(InputStreamReader(exe.errorStream))
            while (true) {
                val temp = bin.readLine()
                res += temp ?: break
            }
            if (res == "") {
                while (true) {
                    val temp = berr.readLine()
                    res += temp ?: break
                }
            }
            println(res)
            bin.close()
            berr.close()
        } catch (e: Exception) {
            println(e)
        }
        return res
    }

    fun compileTest(labFolder: String, labName: String): String {
        val labPath =
            "C:\\Users\\Bogdan\\Desktop\\project-diplom\\cluster\\xFiles\\database_lab_files\\id-651_sub431_lab2\\id-651_sub431_lab2_Wru_TiQaUNWftniOAotnzWvkQt_dwprLNbgNvZUMpqAXLJbjE"

        val source = "cd $labPath"
        val commandCompilationTest = "mvn compile test"
        val cmd = "cmd /c start cmd.exe /K \"$source & $commandCompilationTest & EXIT\""


        var res = ""
        val exe: Process = Runtime.getRuntime().exec(cmd)

        try {
            exe.waitFor()
            val bin = BufferedReader(InputStreamReader(exe.inputStream))
            val berr = BufferedReader(InputStreamReader(exe.errorStream))
            while (true) {
                val temp = bin.readLine()
                res += temp ?: break
            }
            if (res == "") {
                while (true) {
                    val temp = berr.readLine()
                    res += temp ?: break
                }
            }
            println(res)
            bin.close()
            berr.close()
        } catch (e: Exception) {
            println(e)
        }
        return res
    }

    fun checkLab(labFolder: String, labName: String): TestExecutionSummary {


        val s =
            "C:\\Users\\Bogdan\\Desktop\\project-diplom\\cluster\\build\\classes\\kotlin\\test"

        val request = LauncherDiscoveryRequestBuilder.request()
            .selectors(selectClasspathRoots(setOf(Path.of(s))))
            .filters(includeClassNamePatterns(".*"))
            .build()

        val listener = SummaryGeneratingListener()

        LauncherFactory.openSession().use { session ->
            val launcher = session.launcher
            // Register a listener of your choice
            launcher.registerTestExecutionListeners(listener)
            // Discover tests and build a test plan
            val testPlan = launcher.discover(request)
            // Execute test plan
            launcher.execute(testPlan)
            // Alternatively, execute the request directly
            launcher.execute(request)
        }

        return listener.summary
    }

    companion object {
        const val filesDb = "xFiles\\database_lab_files"
    }

//    fun test3(): Long {
//
//        val request = LauncherDiscoveryRequestBuilder.request()
//            .selectors(selectPackage("com.holovin.cluster.data.service"))
//            .filters(includeClassNamePatterns(".*"))
//            .build()
//
//        val listener = SummaryGeneratingListener()
//
//        LauncherFactory.openSession().use { session ->
//            val launcher = session.launcher
//            // Register a listener of your choice
//            launcher.registerTestExecutionListeners(listener)
//            // Discover tests and build a test plan
//            val testPlan = launcher.discover(request)
//            // Execute test plan
//            launcher.execute(testPlan)
//            // Alternatively, execute the request directly
//            launcher.execute(request)
//        }
//
//        val summary = listener.summary
//        return summary.testsFoundCount
//    }

//    fun test(): Long {
//
//        val request = LauncherDiscoveryRequestBuilder.request()
//            .selectors(DiscoverySelectors.selectUri("jetbrains://idea/navigate/reference?project=cluster&fqn=com.holovin.cluster"))
//            .build()
//
//        val launcher = LauncherFactory.create()
//        val listener = SummaryGeneratingListener()
//        launcher.registerTestExecutionListeners(listener)
//        launcher.execute(request)
//
//        val summary = listener.summary
//
//        println("testsFoundCount " + summary.testsFoundCount)
//        println("testsSucceededCount - " + summary.testsSucceededCount)
//        println("failures - " + summary.failures)
//
//        return summary.testsFoundCount
////        failures.forEach(Consumer { failure: TestExecutionSummary.Failure -> println("failure - " + failure.exception) })
//    }

}
