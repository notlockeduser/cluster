package com.holovin.cluster.full.use.cases

import com.holovin.cluster.user.service.UserService
import com.holovin.cluster.user.service.domain.LabData
import com.holovin.cluster.user.service.domain.UserData
import com.holovin.cluster.user.service.domain.UserRole
import net.lingala.zip4j.ZipFile
import org.apache.commons.lang3.RandomStringUtils.*
import org.junit.jupiter.api.Assertions
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
        val userData = UserData(role = UserRole.STUDENT)
        val labData = LabData()

        val archiveZip = ZipFile(zipTemplate + "zip_${randomAlphabetic(10)}.zip")
        archiveZip.addFolder(File(inputTestLab))

        val result = userService.addLabAndCheck(userData, labData, archiveZip)

        println("---Result  ====  $result")
        Assertions.assertNotNull(result)
    }

    companion object {
        const val zipTemplate = "C:\\Users\\Bogdan\\Desktop\\project-diplom\\cluster\\zip_files\\"
        const val inputTestLab = "C:\\Users\\Bogdan\\Desktop\\project-diplom\\cluster\\internet_files\\project_test"
    }
}
