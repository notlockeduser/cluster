package com.holovin.cluster.user.service

import com.holovin.cluster.data.service.DataService
import com.holovin.cluster.plagiarist.service.PlagiarismService
import com.holovin.cluster.user.service.domain.LabData
import com.holovin.cluster.user.service.domain.LabFolder
import com.holovin.cluster.user.service.domain.UserData
import com.holovin.cluster.user.service.domain.UserRole
import com.holovin.cluster.user.service.mongo.MongoDb
import net.lingala.zip4j.ZipFile
import org.springframework.stereotype.Component

@Component
class UserService(
    val plagiarismService: PlagiarismService,
    val dataService: DataService,
    val mongoDb: MongoDb
) {

    // Common
    fun addUser(mongoUser: UserData) {
        mongoDb.addUser(mongoUser)
    }

    // Student
    fun addLab(studentId: String, labData: LabData, archiveLab: ZipFile) {

        val userData = mongoDb.getUserById(studentId)

        require(userData.role == UserRole.STUDENT) { "this command for STUDENT, id = $studentId" }

        // Check if student has access to folder
        val labFolder = labData.createLabFolder()
        require(userData.acceptedFolders.any { it == labFolder }) {
            "you does not have access to folder, accepted folder = ${userData.acceptedFolders} " +
                    "require folder = $labFolder"
        }

        // data service
        dataService.saveLab(archiveLab, labData.createNameLab())
    }

    fun checkLab(studentId: String, labData: LabData): String {

        val userData = mongoDb.getUserById(studentId)

        require(userData.role == UserRole.STUDENT) { "this command for STUDENT, id = $studentId" }

        // plagiarism service
        val resultOfCheck = plagiarismService.checkLab(labData.createNameLab())
        println(resultOfCheck)

        return resultOfCheck.toString()
    }

    fun getListOfAccessFolders(studentId: String): List<LabFolder> {
        return mongoDb.getUserById(studentId).acceptedFolders.toList()
    }

    // Teacher
    fun updateStudentAccess(teacherUserData: UserData, emailStudent: String, labFolder: LabFolder) {
        require(teacherUserData.role == UserRole.TEACHER) { "this command for TEACHER, userData = $teacherUserData" }

        // check if student is exists
        require(mongoDb.existsUserByEmail(emailStudent)) { "Student with this email is not exists, emailStudent = $emailStudent" }

        // add access (update field acceptedFolders)
        mongoDb.getUserByEmail(emailStudent).acceptedFolders.add(labFolder)
    }
}
