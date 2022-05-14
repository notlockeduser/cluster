package com.holovin.cluster.full.use.cases

import com.holovin.cluster.user.service.domain.UserData
import org.jeasy.random.EasyRandom

private val EASY_RANDOM = EasyRandom()

internal fun UserData.Companion.random() = EASY_RANDOM.nextObject(UserData::class.java)


