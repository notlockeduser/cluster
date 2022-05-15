package com.holovin.cluster.user.service.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "student_data")
data class StudentData(
    @Id val id: String,
    val name: String,
    val surname: String,
    val group: String,
    val password: String,
    val email: String,
    val acceptedFolders: MutableSet<LabFolder> = mutableSetOf()
) {

    companion object
}
