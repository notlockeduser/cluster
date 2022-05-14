package com.holovin.cluster.full.use.cases

import com.holovin.cluster.user.service.UserService
import com.holovin.cluster.user.service.domain.LabData
import com.holovin.cluster.user.service.domain.LabFolder
import com.holovin.cluster.user.service.domain.UserData
import com.holovin.cluster.user.service.domain.UserRole
import net.lingala.zip4j.ZipFile
import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
internal class E2E {

    @Autowired
    lateinit var userService: UserService

    @Test
    fun `should save lab and give result when student add lab`() {
        // GIVEN
        val studentData = UserData.random().copy(UserRole.STUDENT)
        val teacherData = UserData.random().copy(UserRole.TEACHER)
        val labFolder = LabFolder(teacherData.id, "lab1", studentData.group)
        val labData = LabData(teacherData.id, "lab1", studentData.group, studentData.surname, studentData.name)
        val zipFile = createZipFile()

        // registration
        userService.addUser(studentData)
        userService.addUser(teacherData)

        // add student access to folder
        userService.updateStudentAccess(teacherData, studentData.email, labFolder)

        // student add lab
        userService.addLab(studentData.id, labData, zipFile)

        // student check lab
        val result = userService.checkLab(studentData.id, labData)

        println("---Result  ====  $result")
        assertThat(result).isNotNull()
    }

    private fun createZipFile(): ZipFile {
        val archiveZip = ZipFile(zipTemplate + "zip_${randomAlphabetic(10)}.zip")
        archiveZip.addFolder(File(inputTestLab))
        return archiveZip
    }

    companion object {
        const val zipTemplate = "C:\\Users\\Bogdan\\Desktop\\project-diplom\\cluster\\zip_files\\"
        const val inputTestLab = "C:\\Users\\Bogdan\\Desktop\\project-diplom\\cluster\\internet_files\\project_test"
    }
}

