package com.holovin.cluster.user.service.domain

class LabData(
    private val idTeacher: String,
    private val labNumber: String,
    private val group: String,
    private val surname: String,
    private val name: String
) {
    fun createNameFolder(): String {
        return idTeacher + "_" + labNumber + "_" + group
    }

    fun createNameLab(): String {
        return idTeacher + "_" + labNumber + "_" + group + "_" + surname + "_" + name
    }

    companion object
}
