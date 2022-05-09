package com.holovin.cluster.user.service

import com.holovin.cluster.data.service.DataService
import com.holovin.cluster.plagiarist.service.PlagiaristService
import com.holovin.cluster.user.service.domain.UserData
import com.holovin.cluster.user.service.domain.UserRole
import net.lingala.zip4j.ZipFile
import org.springframework.stereotype.Component

@Component
class UserServiceImpl(
    val plagiaristService: PlagiaristService,
    val dataService: DataService
) : UserService {

    override fun addLab(userData: UserData, lab: ZipFile): String {
        require(userData.role == UserRole.STUDENT) { "this command for STUDENT, userData = $userData" }

        val nameLab = dataService.saveLab(lab)
        val resultOfCheck = plagiaristService.checkLab(nameLab)
        println(resultOfCheck)
        return resultOfCheck.result
    }
}
