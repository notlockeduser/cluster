package com.holovin.cluster.plagiarist.service

import org.junit.jupiter.api.Test

internal class PlagiaristServiceImplTest {

    @Test
    fun checkFile() {
        val plagiaristServiceImpl = PlagiaristServiceImpl()
        plagiaristServiceImpl.checkFiles()
    }

}