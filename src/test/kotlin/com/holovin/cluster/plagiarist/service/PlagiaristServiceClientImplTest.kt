package com.holovin.cluster.plagiarist.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PlagiaristServiceClientImplTest {

    @Test
    fun checkFile() {
        val plagiaristServiceClientImpl = PlagiaristServiceClientImpl()
        plagiaristServiceClientImpl.checkFile()
    }

}