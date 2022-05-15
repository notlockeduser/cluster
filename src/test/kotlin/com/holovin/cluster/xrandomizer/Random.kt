package com.holovin.cluster.xrandomizer

import com.holovin.cluster.user.service.domain.LabData
import com.holovin.cluster.user.service.domain.StudentData
import com.holovin.cluster.user.service.domain.TeacherData
import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
import org.jeasy.random.EasyRandom
import kotlin.random.Random

private val EASY_RANDOM = EasyRandom()

internal fun StudentData.Companion.random() = EASY_RANDOM.nextObject(StudentData::class.java)
internal fun TeacherData.Companion.random() = EASY_RANDOM.nextObject(TeacherData::class.java)

internal fun LabData.Companion.random() = LabData(
    idTeacher = "id-" + Random.nextInt(100, 999),
    subject = "sub" + Random.nextInt(100, 999),
    labNumber = "lab" + Random.nextInt(6),
    group = randomAlphabetic(2).uppercase() + "-" + Random.nextInt(10, 99),
    surname = "s" + randomAlphabetic(3).uppercase(),
    name = "n" + randomAlphabetic(2)
)