package com.holovin.cluster.user.service.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "student_data")
data class StudentData(
    @Id val id: String,
    var name: String,
    var surname: String,
    var group: String,
    var password: String,
    var email: String,
    val acceptedFolders: MutableSet<LabFolder> = mutableSetOf()
) {

    companion object
}
