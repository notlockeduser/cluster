package com.holovin.cluster.gw.service

import com.holovin.cluster.gw.service.domain.CreateLabDto
import com.holovin.cluster.gw.service.domain.LabDescription
import com.holovin.cluster.user.service.UserService
import com.holovin.cluster.user.service.domain.LabData
import com.holovin.cluster.user.service.domain.LabFolder
import com.holovin.cluster.user.service.domain.StudentData
import com.holovin.cluster.user.service.domain.TeacherData
import com.holovin.cluster.user.service.mongo.TeacherDataRepository
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import java.security.Principal


@Controller
@RequestMapping
class GwController(
    private val userService: UserService,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
    private val teacherDataRepository: TeacherDataRepository
) {

    @GetMapping("/hello")
    fun hello(): String {
        return "hello"
    }

    // registration

    @GetMapping("/registration")
    fun getRegistration(): String {
        return "registration"
    }

    @GetMapping("/add_student")
    fun getAddStudent(model: Model): String {
        model.addAttribute("student", StudentData())
        return "add_student"
    }

    @PostMapping("/add_student")
    fun postAddStudent(@ModelAttribute studentData: StudentData): String {
        studentData.password = bCryptPasswordEncoder.encode(studentData.password)
        userService.addStudent(studentData)
        return "redirect:/login"
    }

    @GetMapping("/add_teacher")
    fun getAddTeacher(model: Model): String {
        model.addAttribute("teacher", TeacherData())
        return "add_teacher"
    }

    @PostMapping("/add_teacher")
    fun postAddTeacher(@ModelAttribute teacherData: TeacherData): String {
        teacherData.password = bCryptPasswordEncoder.encode(teacherData.password)
        userService.addTeacher(teacherData)
        return "redirect:/login"
    }

    // profile

    @GetMapping("/profile")
    fun getProfileTeacher(principal: Principal, model: Model): String {

        if (userService.existsStudentFromDbByEmail(principal.name)) {
            model.addAttribute("student", userService.getStudentFromDbByEmail(principal.name))
            return "profile_student"
        }
        if (userService.existsTeacherFromDbByEmail(principal.name)) {
            model.addAttribute("teacher", userService.getTeacherFromDbByEmail(principal.name))
            return "profile_teacher"
        }

        return ResponseEntity.badRequest().toString()
    }

    ////////

    @GetMapping("/create_lab")
    fun getCreateLab(model: Model): String {
        model.addAttribute("lab_settings", CreateLabDto())
        return "create_lab"
    }

    @PostMapping("/create_lab")
    fun postCreateLab(principal: Principal, @ModelAttribute createLabDto: CreateLabDto, model: Model): String {
        userService.createLab(
            principal.name,
            LabFolder(principal.name, createLabDto.subject, createLabDto.labNumber),
            createLabDto.description
        )
        return "redirect:/profile"
    }

    @GetMapping("/get_lab_by_teacher/{email}/{subject}/{labNumber}")
    fun getLabByTeacher(
        @PathVariable("email") email: String,
        @PathVariable("subject") subject: String,
        @PathVariable("labNumber") labNumber: String,
        model: Model
    ): String {
        model.addAttribute("lab", LabDescription())
        return "get_lab_by_teacher"
    }

//
//    @PostMapping("/add_teacher")
//    fun addTeacher(
//        @RequestParam name: String,
//        @RequestParam surname: String,
//        @RequestParam password: String,
//        @RequestParam email: String
//    ): ResponseEntity<String> {
//        val teacherData = TeacherData(name, surname, password, email)
//        val addTeacherResponse = userService.addTeacher(teacherData)
//        return ResponseEntity.ok("Teacher add = $addTeacherResponse")
//    }

    @PostMapping("/upload_by_student")
    fun uploadZipLabByStudent(
        @RequestParam file: MultipartFile,
        @RequestParam studentId: String,
        @RequestParam labData: LabData
    ): ResponseEntity<String> {
        try {
            if (file.contentType != "application/zip") return ResponseEntity.badRequest()
                .body("Type of upload files need = application/zip, current = " + file.contentType)

            userService.addLab(studentId, labData, file)


            return ResponseEntity.ok("Upload file successfully: " + file.originalFilename)
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body("Could not upload the file:" + file.originalFilename)
        }
    }
}