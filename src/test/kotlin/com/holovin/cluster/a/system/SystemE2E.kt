package com.holovin.cluster.a.system

import com.holovin.cluster.full.use.cases.random
import com.holovin.cluster.user.service.UserService
import com.holovin.cluster.user.service.domain.LabData
import com.holovin.cluster.user.service.domain.LabFolder
import com.holovin.cluster.user.service.domain.StudentData
import com.holovin.cluster.user.service.domain.TeacherData
import com.holovin.cluster.user.service.mongo.StudentDataRepository
import net.lingala.zip4j.ZipFile
import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
internal class SystemE2E {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var studentDataRepository: StudentDataRepository

    @Test
    fun `should save lab and give result when student add lab`() {
        // GIVEN
        val studentData = StudentData.random()
        val teacherData = TeacherData.random()
        val labFolder = LabFolder(teacherData.id, "lab1", studentData.group)
        val labData = LabData(teacherData.id, "lab1", studentData.group, studentData.surname, studentData.name)
        val zipFile = createZipFile()

        // registration
        userService.addStudent(studentData)
        userService.addTeacher(teacherData)

        // add student access to folder
        userService.updateStudentAccessByEmail(teacherData.id, studentData.email, labFolder)

        // student add lab
        userService.addLab(studentData.id, labData, zipFile)

        // student check lab
        val result = userService.checkLab(studentData.id, labData)

        println("---Result  ====  $result")
        assertThat(result).isNotNull
    }

    private fun createZipFile(): ZipFile {
        val archiveZip = ZipFile(zipTemplate + "zip_${randomAlphabetic(10)}.zip")
        archiveZip.addFolder(File(inputTestLab))
        return archiveZip
    }

    companion object {
        const val zipTemplate = "xFiles\\input_zip_files\\"
        const val inputTestLab = "xFiles\\input_lab_files\\project_test"
    }
}
