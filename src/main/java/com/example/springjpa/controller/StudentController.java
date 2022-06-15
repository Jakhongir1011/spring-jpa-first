package com.example.springjpa.controller;

import com.example.springjpa.model.Student;
import com.example.springjpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
;
import java.util.List;
import java.util.Optional;

// RestController ham bean qiluvchi annatatsiya
@RestController
public class StudentController {

    // bean ni chaqirivchi anatatsiya
    // anashu Dependensy injekshin
    @Autowired
    StudentRepository studentRepository;


    // C R* U D
    // Create -> POST
    // RED    -> GET
    // UPDATE  -> PUT
    // DELETE   -> DELETE

    // READ ALL LIST
    //jpaRepositoryda List ga olgin degani uchun Student bolyapti
    @RequestMapping(value = "/student",method = RequestMethod.GET)
    public List<Student> getStudent(){
        List<Student> all = studentRepository.findAll();// Studentni olganini
    // sababi biz interface StudentRepositoryda 10 qatorda Student deb yozganmiz
        return all;
    }

    // PUT ADDED STUDENT
    @RequestMapping(value = "/student",method = RequestMethod.POST)
    public String addStudent(@RequestBody Student student){
        studentRepository.save(student);
        return "added Student";
    }

    // GET STUDENT BY ID
    @RequestMapping(value = "/student/{id}",method = RequestMethod.GET)
    public Student getStudentById(@PathVariable Integer id){
        Optional<Student> byId = studentRepository.findById(id);
        if (byId.isPresent()){
            Student student = byId.get();
            return student;
        }
        return new Student();
    }

   // Delete Student By Id
    @RequestMapping(value = "/student/{id}",method = RequestMethod.DELETE)
    public String deleteStudentById(@PathVariable Integer id){
        studentRepository.deleteById(id);
        return "delete Student";
    }

    // Update PUT method
    @RequestMapping(value = "/student/{id}",method = RequestMethod.PUT)
    public String editStudent(@PathVariable Integer id, @RequestBody Student student){
        // bu qutini ichida student qaytaradi
        // bolmasligi mumkin shuning uchun Optional boladi
        Optional<Student> byId = studentRepository.findById(id);
        if (byId.isPresent()){
            Student editingStudent = byId.get();
            editingStudent.setFirstName(student.getFirstName());
            editingStudent.setLastName(student.getLastName());
            editingStudent.setPhoneNumber(student.getPhoneNumber());
            studentRepository.save(editingStudent);
            return "editing student";
            // ager aytadi save shunday boladi tagida shunday kod yozolganki
            // agar manga save bolgan objectni id si bolsa update jonataman
            // bolmasa insert jonataman deb yozib qoyilgan
        }
            return " editing not student";
    }
}


