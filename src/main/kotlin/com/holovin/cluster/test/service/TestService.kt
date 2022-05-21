package com.holovin.cluster.test.service

import org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns
import org.junit.platform.engine.discovery.DiscoverySelectors
import org.junit.platform.engine.discovery.DiscoverySelectors.selectClass
import org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder
import org.junit.platform.launcher.core.LauncherFactory
import org.junit.platform.launcher.listeners.SummaryGeneratingListener
import org.springframework.stereotype.Component


@Component
class TestService {


    fun test(): Long {

        val request = LauncherDiscoveryRequestBuilder.request()
            .selectors(DiscoverySelectors.selectUri("jetbrains://idea/navigate/reference?project=cluster&fqn=com.holovin.cluster"))
            .build()

        val launcher = LauncherFactory.create()
        val listener = SummaryGeneratingListener()
        launcher.registerTestExecutionListeners(listener)
        launcher.execute(request)

        val summary = listener.summary

        println("testsFoundCount " + summary.testsFoundCount)
        println("testsSucceededCount - " + summary.testsSucceededCount)
        println("failures - " + summary.failures)

        return summary.testsFoundCount
//        failures.forEach(Consumer { failure: TestExecutionSummary.Failure -> println("failure - " + failure.exception) })
    }

    fun <T> test2(anyClass: Class<T>): Long {
        val request = LauncherDiscoveryRequestBuilder.request()
            .selectors(
                selectClass(anyClass),
            )
//            .filters(
//                includeClassNamePatterns(".*Tests")
//            )
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

        val summary = listener.summary
        return summary.testsFoundCount
    }

    fun test3(): Long {

        val request = LauncherDiscoveryRequestBuilder.request()
            .selectors(selectPackage("com.holovin.cluster"))
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

        val summary = listener.summary
        return summary.testsFoundCount
    }
}
