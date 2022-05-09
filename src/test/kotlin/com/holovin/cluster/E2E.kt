package com.holovin.cluster

import com.holovin.cluster.data.service.DataService
import com.holovin.cluster.plagiarist.service.PlagiaristService
import com.holovin.cluster.user.service.UserService
import com.holovin.cluster.user.service.domain.UserData
import com.holovin.cluster.user.service.domain.UserRole
import net.lingala.zip4j.ZipFile
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
internal class E2E{

    @Autowired
    lateinit var dataService: DataService

    @Autowired
    lateinit var plagiaristService: PlagiaristService

    @Autowired
    lateinit var userService: UserService

    @Test
    fun shouldSaveLabAndGiveResultWhenStudentAddLab(){
        // GIVEN
        val userData = UserData(role = UserRole.STUDENT)

        val nameProjectToSave = "C:\\Users\\Bogdan\\Desktop\\project-diplom\\cluster\\internet_files\\project_test"
        val zipToSave = ZipFile("filename.zip")
        zipToSave.addFolder(File(nameProjectToSave))

//        userService.addLab(userData, zipToSave)



    }
}