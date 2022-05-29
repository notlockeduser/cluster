package com.holovin.cluster.user.service.domain

data class LabFolder(
    val idTeacher: String? = null,
    val subject: String? = null,
    val labNumber: String? = null
) {

    fun createNameFolder(): String {
        return idTeacher + "_" + subject + "_" + labNumber
    }

    companion object
}
