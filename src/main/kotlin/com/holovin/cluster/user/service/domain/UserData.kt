package com.holovin.cluster.user.service.domain

import org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric

class UserData(
    id: String = randomAlphanumeric(10),
    name: String =  randomAlphanumeric(10),
    val role: UserRole
)
