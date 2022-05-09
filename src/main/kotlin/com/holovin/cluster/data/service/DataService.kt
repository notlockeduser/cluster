package com.holovin.cluster.data.service

import net.lingala.zip4j.ZipFile
import java.io.File

interface DataService {

    fun saveLab(file: File): String

    fun saveLab(file : ZipFile): String
}
