package com.holovin.cluster.user.service

import com.holovin.cluster.data.service.DataService
import com.holovin.cluster.plagiarism.service.PlagiarismService
import com.holovin.cluster.user.service.domain.LabData
import com.holovin.cluster.user.service.domain.LabFolder
import com.holovin.cluster.user.service.domain.StudentData
import com.holovin.cluster.user.service.domain.TeacherData
import com.holovin.cluster.user.service.mongo.StudentDataRepository
import com.holovin.cluster.user.service.mongo.TeacherDataRepository
import net.lingala.zip4j.ZipFile
import org.springframework.stereotype.Component

@Component
class UserService(
    val plagiarismService: PlagiarismService,
    val dataService: DataService,
    val studentDataRepository: StudentDataRepository,
    val teacherDataRepository: TeacherDataRepository
) {

    ////////////////////////////// Student

    fun addStudent(studentData: StudentData) {
        studentDataRepository.save(studentData)
    }

    fun updateStudent(studentData: StudentData) {
        studentDataRepository.save(studentData)
    }

    fun addLab(studentId: String, labData: LabData, archiveLab: ZipFile) {

        val studentData = getStudentFromDb(studentId)

        // Check if student has access to folder
        val labFolder = labData.createLabFolder()
        require(studentData.acceptedFolders.any { it == labFolder }) {
            "you does not have access to folder, accepted folder = ${studentData.acceptedFolders} " +
                    "require folder = $labFolder"
        }

        // data service
        dataService.saveLab(archiveLab, labData.createNameLabFolder(), labData.createNameLab())
    }

    fun checkLab(studentId: String, labData: LabData): String {

        getStudentFromDb(studentId)

        // plagiarism service
        val resultOfCheck = plagiarismService.checkLab(labData.createNameLab())
        println(resultOfCheck)

        return resultOfCheck.toString()
    }

    fun getListOfAccessFolders(studentId: String): List<LabFolder> {
        return getStudentFromDb(studentId).acceptedFolders.toList()
    }

    // Teacher
    fun addTeacher(teacherData: TeacherData) {
        teacherDataRepository.save(teacherData)
    }

    fun updateTeacher(teacherData: TeacherData) {
        teacherDataRepository.save(teacherData)
    }

    fun updateStudentAccessByEmail(teacherId: String, studentEmail: String, labFolder: LabFolder) {

        val teacherData = getTeacherFromDb(teacherId)
        val studentData = getStudentFromDbByEmail(studentEmail)

        // add access (update field acceptedFolders)
        studentData.acceptedFolders.add(labFolder)
        studentDataRepository.save(studentData)
    }

    fun updateStudentsAccessByGroup(teacherId: String, group: String, labFolder: LabFolder) {

        val teacherData = getTeacherFromDb(teacherId)

        // add access (update field acceptedFolders)
        val studentInGroup = studentDataRepository.findAllByGroup(group)
        studentInGroup.map { it.acceptedFolders.add(labFolder) }
        studentDataRepository.saveAll(studentInGroup)
    }

    fun getResultOfLabFolder(teacherId: String, labFolder: LabFolder): String {

        val teacherData = getTeacherFromDb(teacherId)

        // plagiarism service
        val resultOfCheck = plagiarismService.checkFiles(labFolder.createNameFolder())
        println(resultOfCheck)

        return resultOfCheck
    }

    fun addLabsByTeacher(teacherId: String, labFolder: LabFolder, archiveLab: ZipFile) {

        val teacherData = getTeacherFromDb(teacherId)

        // data service
        dataService.saveLabs(archiveLab, labFolder.createNameFolder())
    }

    // utils
    private fun getStudentFromDb(studentId: String): StudentData {
        val studentDataOptional = studentDataRepository.findById(studentId)
        require(studentDataOptional.isPresent) { "No such STUDENT with id = $studentId" }
        return studentDataOptional.get()
    }

    private fun getStudentFromDbByEmail(studentEmail: String): StudentData {
        val studentDataOptional = studentDataRepository.findByEmail(studentEmail)
        require(studentDataOptional.isPresent) { "No such STUDENT with email = $studentEmail" }
        return studentDataOptional.get()
    }

    private fun getTeacherFromDb(teacherId: String): TeacherData {
        val teacherDataOptional = teacherDataRepository.findById(teacherId)
        require(teacherDataOptional.isPresent) { "No such TEACHER with id = $teacherId" }
        return teacherDataOptional.get()
    }
}
