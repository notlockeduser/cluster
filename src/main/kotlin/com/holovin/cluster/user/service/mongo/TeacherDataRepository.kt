package com.holovin.cluster.user.service.mongo

import com.holovin.cluster.user.service.domain.TeacherData
import org.springframework.data.mongodb.repository.MongoRepository

interface TeacherDataRepository : MongoRepository<TeacherData, Any>
