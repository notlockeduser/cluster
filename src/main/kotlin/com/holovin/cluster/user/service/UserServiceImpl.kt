package com.holovin.cluster.user.service

import com.holovin.cluster.data.service.DataService
import com.holovin.cluster.plagiarist.service.PlagiarismService
import com.holovin.cluster.user.service.domain.LabData
import com.holovin.cluster.user.service.domain.UserData
import com.holovin.cluster.user.service.domain.UserRole
import net.lingala.zip4j.ZipFile
import org.springframework.stereotype.Component

@Component
class UserServiceImpl(
    val plagiarismService: PlagiarismService,
    val dataService: DataService
) : UserService {

    override fun addLabAndCheck(userData: UserData, labData: LabData, archiveLab: ZipFile): String {
        require(userData.role == UserRole.STUDENT) { "this command for STUDENT, userData = $userData" }

        // data service
        val labName = createNameLab(userData, labData)
        dataService.saveLab(archiveLab, labName)

        // plagiarism service
        val resultOfCheck = plagiarismService.checkLab(labName)
        println(resultOfCheck)

        return resultOfCheck.toString()
    }

    fun createNameLab(userData: UserData, labData: LabData): String {
        return userData.name + "_" + labData.labName + "_" + labData.group
    }
}
