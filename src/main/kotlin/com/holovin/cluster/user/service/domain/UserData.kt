package com.holovin.cluster.user.service.domain

class UserData(
    val id: String,
    val name: String,
    val surname: String,
    val group: String,
    val password: String,
    val role: UserRole,
    val email: String,
    val acceptedFolders: MutableSet<String> = mutableSetOf()
) {
    fun copy(newRole: UserRole): UserData {
        return UserData(id, name, surname, group, password, newRole, email, acceptedFolders)
    }

    companion object
}