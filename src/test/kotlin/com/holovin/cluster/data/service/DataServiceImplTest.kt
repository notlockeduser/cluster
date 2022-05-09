package com.holovin.cluster.data.service

import net.lingala.zip4j.ZipFile
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
internal class DataServiceImplTest {

    @Autowired
    lateinit var dataService: DataService

    @Test
    fun saveWithToken() {
        // user create zip file and put into service
        val nameProjectToSave = "C:\\Users\\Bogdan\\Desktop\\project-diplom\\cluster\\internet_files\\project_test"
        val zipToSave = ZipFile("filename.zip")
        zipToSave.addFolder(File(nameProjectToSave))

        dataService.saveLab(zipToSave)
    }
}