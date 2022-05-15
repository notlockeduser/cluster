package com.holovin.cluster.user.service.domain

data class LabData(
    private val idTeacher: String,
    private val subject: String,
    private val labNumber: String,
    private val group: String,
    private val surname: String,
    private val name: String
) {

    fun createNameLab(): String {
        return idTeacher + "_" + subject + "_" + labNumber + "_" + group + "_" + surname + "_" + name
    }

    fun createLabFolder(): LabFolder {
        return LabFolder(idTeacher, subject, labNumber)
    }

    fun createNameLabFolder(): String {
        return LabFolder(idTeacher, subject, labNumber).createNameFolder()
    }

    companion object
}

