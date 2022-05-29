package com.holovin.cluster.user.service.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "teacher_data")
data class TeacherData(
    val name: String? = null,
    val surname: String? = null,
    var password: String? = null,
    val email: String? = null,
    val createdLabs: MutableSet<LabFolder> = mutableSetOf(),
    @Id val id: ObjectId = ObjectId.get(),
) {

    companion object
}
