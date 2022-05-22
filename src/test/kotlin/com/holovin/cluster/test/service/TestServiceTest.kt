package com.holovin.cluster.test.service

import org.junit.jupiter.api.Test

class TestServiceTest {

    private val testService = TestService()
//
//    @Test
//    fun test() {
//        val resultTestsStudent = testService.checkLab("21", "2")
//        println("--- Result tests by student  ====  $resultTestsStudent")
//        assertThat(resultTestsStudent).isNotNull
//
//        assertThat(resultTestsStudent.testsFoundCount).isEqualTo(3)
//        assertThat(resultTestsStudent.testsFailedCount).isEqualTo(1)
//        assertThat(resultTestsStudent.testsSucceededCount).isEqualTo(2)
//        println(resultTestsStudent.failures.map { println(it) })
//    }

    @Test
    fun testRun() {
        val process = testService.compileSrc(
            "id-651_sub431_lab2",
            "id-651_sub431_lab2_UfzQhdgLLfDTDGspDb_gNfZBdyFGRajVfJNonEnOinZj_LlN"
        )

        println(process)

        val process2 = testService.compileTest(
            "id-651_sub431_lab2",
            "id-651_sub431_lab2_UfzQhdgLLfDTDGspDb_gNfZBdyFGRajVfJNonEnOinZj_LlN"
        )

        println(process2)
    }
}
