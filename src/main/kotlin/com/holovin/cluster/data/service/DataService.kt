package com.holovin.cluster.data.service

import net.lingala.zip4j.ZipFile
import org.springframework.stereotype.Component

@Component
class DataService {

    fun saveLab(archiveLab: ZipFile, labFolder: String, labName: String) {
        archiveLab.renameFile(archiveLab.fileHeaders.last(), labName)
        archiveLab.extractAll(rootFolder + "\\" + labFolder)
    }

    fun saveLabs(archiveLab: ZipFile, labFolder: String) {
        archiveLab.extractAll(rootFolder + "\\" + labFolder)
    }

    companion object {
        const val rootFolder = "xFiles\\database_lab_files"
    }
}
