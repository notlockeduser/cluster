package com.holovin.cluster.data.service

import net.lingala.zip4j.ZipFile
import org.springframework.stereotype.Component
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

@Component
class DataServiceImpl : DataService {
    override fun saveLab(file: File): String {
        TODO("Not yet implemented")
    }

    override fun saveLab(file: ZipFile): String {
        file.extractAll("C:\\Users\\Bogdan\\Desktop\\project-diplom\\cluster\\files")
        return "project_test"
    }

}
