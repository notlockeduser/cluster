package com.holovin.cluster.full.use.cases

import com.holovin.cluster.user.service.domain.StudentData
import com.holovin.cluster.user.service.domain.TeacherData
import org.jeasy.random.EasyRandom

private val EASY_RANDOM = EasyRandom()

internal fun StudentData.Companion.random() = EASY_RANDOM.nextObject(StudentData::class.java)
internal fun TeacherData.Companion.random() = EASY_RANDOM.nextObject(TeacherData::class.java)


