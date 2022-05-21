package com.holovin.cluster.test.service

import com.holovin.cluster.data.service.DataServiceTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TestServiceTest {

    private val testService = TestService()

    @Test
    fun test() {
        val test = testService.test()

        assertThat(test).isNotEqualTo(0)
    }

    @Test
    fun test2() {
        val test = testService.test2(DataServiceTest::class.java)

        assertThat(test).isNotEqualTo(0)
    }

    @Test
    fun test3() {
        val test = testService.test3()

        assertThat(test).isNotEqualTo(0)
    }
}
