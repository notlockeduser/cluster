package com.holovin.cluster.user.service.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "teacher_data")
data class TeacherData(
    @Id val id: String,
    val name: String,
    val surname: String,
    val group: String,
    val password: String,
    val email: String,
) {

    companion object
}
