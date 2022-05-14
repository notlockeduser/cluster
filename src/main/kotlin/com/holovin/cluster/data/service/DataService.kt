package com.holovin.cluster.data.service

import net.lingala.zip4j.ZipFile
import org.springframework.stereotype.Component

@Component
class DataService {

    fun saveLab(archiveLab: ZipFile, labName: String) {
        archiveLab.renameFile(archiveLab.fileHeaders.last(), labName)
        archiveLab.extractAll(folder)
    }

    fun saveLabs(archiveLab: ZipFile, labFolder: String) {
        archiveLab.fileHeaders.map { archiveLab.renameFile(it, labFolder + it.fileName) }
        archiveLab.extractAll(folder)
    }

    companion object {
        const val folder = "xFiles\\database_lab_files"
    }
}
