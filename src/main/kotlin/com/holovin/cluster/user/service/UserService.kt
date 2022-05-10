package com.holovin.cluster.user.service

import com.holovin.cluster.user.service.domain.LabData
import com.holovin.cluster.user.service.domain.UserData
import net.lingala.zip4j.ZipFile

interface UserService {

    fun addLabAndCheck(userData: UserData, labData: LabData, archiveLab: ZipFile): String
}
