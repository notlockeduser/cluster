package com.holovin.cluster.user.service.domain

data class LabFolder(
    val idTeacher: String,
    val subject: String,
    val labNumber: String
) {

    fun createNameFolder(): String {
        return idTeacher + "_" + subject + "_" + labNumber
    }

    companion object
}
