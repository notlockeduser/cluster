package com.holovin.cluster.user.service.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "student_data")
data class StudentData(
    var name: String? = null,
    var surname: String? = null,
    var group: String? = null,
    var password: String? = null,
    var email: String? = null,
    val acceptedFolders: MutableSet<LabFolder> = mutableSetOf(),
    @Id val id: ObjectId = ObjectId.get()
) {

    companion object
}
