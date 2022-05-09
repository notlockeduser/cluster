package com.holovin.cluster.user.service

import com.holovin.cluster.data.service.DataService
import com.holovin.cluster.plagiarist.service.PlagiaristService
import com.holovin.cluster.plagiarist.service.PlagiaristServiceImplTest
import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class UserServiceImplTest{

    @Autowired
    lateinit var dataService: DataService

    @Autowired
    lateinit var plagiaristService: PlagiaristService

    @Autowired
    lateinit var userService: UserService

}