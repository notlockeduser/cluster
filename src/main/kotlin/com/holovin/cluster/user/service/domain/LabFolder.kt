package com.holovin.cluster.user.service.domain

class LabFolder(
    private val idTeacher: String,
    private val labNumber: String,
    private val group: String
) {
    fun createNameFolder(): String {
        return idTeacher + "_" + labNumber + "_" + group
    }

    companion object
}
