package com.holovin.cluster.user.service.domain

import org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric

class LabData(
    val labName: String = randomAlphanumeric(10),
    val group: String = randomAlphanumeric(10)
)