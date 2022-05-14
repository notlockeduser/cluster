package com.holovin.cluster.user.service.mongo

import com.holovin.cluster.user.service.domain.UserData
import org.springframework.stereotype.Component

@Component
class MongoDb {
    val userDataBase = mutableListOf<UserData>()

    fun addUser(user: UserData) {
        userDataBase.add(user)
    }

    fun getUserById(id: String): UserData {
       return userDataBase.first { it.id == id }
    }
}

