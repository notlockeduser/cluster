package com.holovin.cluster.user.service

import com.holovin.cluster.data.service.DataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
internal class UserServiceTest{

    @Autowired
    lateinit var userService: UserService

    @MockBean
    lateinit var dataService: DataService

}
