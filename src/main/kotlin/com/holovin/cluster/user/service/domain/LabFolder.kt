package com.holovin.cluster.user.service.domain

data class LabFolder(
    val idTeacher: String,
    val labNumber: String,
    val group: String
) {

    fun createNameFolder(): String {
        return idTeacher + "_" + labNumber + "_" + group
    }

    companion object
}
