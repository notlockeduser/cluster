package com.holovin.cluster.user.service.domain

import org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric

class UserData(
    val id: String = randomAlphanumeric(10),
    val name: String =  randomAlphanumeric(10),
    val role: UserRole
)
