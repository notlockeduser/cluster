package com.holovin.cluster.user.service

import com.holovin.cluster.user.service.domain.UserData
import net.lingala.zip4j.ZipFile

interface UserService {

    fun addLab(userData: UserData, lab: ZipFile): String
}
