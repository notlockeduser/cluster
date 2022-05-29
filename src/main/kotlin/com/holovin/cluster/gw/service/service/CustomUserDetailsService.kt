package com.holovin.cluster.gw.service.service

import com.holovin.cluster.user.service.domain.StudentData
import com.holovin.cluster.user.service.domain.TeacherData
import com.holovin.cluster.user.service.mongo.StudentDataRepository
import com.holovin.cluster.user.service.mongo.TeacherDataRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

@Service
class CustomUserDetailsService(
    private val studentDataRepository: StudentDataRepository,
    private val teacherDataRepository: TeacherDataRepository
) : UserDetailsService {

    @Transactional(readOnly = true)
    override fun loadUserByUsername(username: String): UserDetails {
        val studentDataOptional: Optional<StudentData> = studentDataRepository.findByEmail(username)
        val teacherDataOptional: Optional<TeacherData> = teacherDataRepository.findByEmail(username)

        if (studentDataOptional.isPresent) {
            val studentData = studentDataOptional.get()

            val grantedAuthorities: MutableSet<GrantedAuthority> = HashSet()
            grantedAuthorities.add(SimpleGrantedAuthority("STUDENT"))

            return User(studentData.email, studentData.password, grantedAuthorities)
        }

        if (teacherDataOptional.isPresent) {
            val teacherData = teacherDataOptional.get()

            val grantedAuthorities: MutableSet<GrantedAuthority> = HashSet()
            grantedAuthorities.add(SimpleGrantedAuthority("TEACHER"))

            return User(teacherData.email, teacherData.password, grantedAuthorities)
        }

        return User(null, null, null)
    }
}
