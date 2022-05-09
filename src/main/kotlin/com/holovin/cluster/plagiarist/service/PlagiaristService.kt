package com.holovin.cluster.plagiarist.service

import java.io.File

interface PlagiaristService {

    fun addFiles(files: List<File>)

    fun checkLab(file: String): ResultOfCheck

    fun checkFiles()
}

class ResultOfCheck (val result : String)
