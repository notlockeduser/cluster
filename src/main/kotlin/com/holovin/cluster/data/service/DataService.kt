package com.holovin.cluster.data.service

import net.lingala.zip4j.ZipFile
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.stereotype.Component
import java.io.File

@Component
class DataService {

    fun saveLab(archiveLab: ZipFile, labFolder: String, labName: String) {
        archiveLab.renameFile(archiveLab.fileHeaders.last(), labName)
        archiveLab.extractAll(rootFolder + "\\" + labFolder)
    }

    fun saveLabs(archiveLab: ZipFile, labFolder: String) {
        archiveLab.extractAll(rootFolder + "\\" + labFolder)
    }

    fun getTemplate(labFolder: String): ZipFile {
        val archiveZip = ZipFile(toUpload + "\\" + "zip_${RandomStringUtils.randomAlphabetic(10)}.zip")
        archiveZip.addFolder(File("$rootFolder\\$labFolder\\template"));
        return archiveZip
    }

    companion object {
        const val rootFolder = "xFiles\\database_lab_files"
        const val toUpload = "xFiles\\database_to_upload"
    }
}
