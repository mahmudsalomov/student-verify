package uz.pdp.studentverify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.studentverify.dto.StudentDtoSuper;
import uz.pdp.studentverify.model.Student;
import uz.pdp.studentverify.service.StudentService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student/api")
public class StudentController {
    @Autowired
    private StudentService studentService;


    /** Barcha ro'yhatdan o'tgan studentlarni qaytaruvchi yo'q **/
    @GetMapping("/all")
    public ResponseEntity all(){
        List<Student> studentList=studentService.getAllByActiveTrue();
        List<StudentDtoSuper> dto=new ArrayList<>();
        studentList.forEach(student -> dto.add(new StudentDtoSuper(student.getEmail(),student.getFullName())));
        return ResponseEntity.ok(dto);
    }
}
