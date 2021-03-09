package uz.pdp.studentverify.service;

import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.studentverify.component.CodeGenerator;
import uz.pdp.studentverify.dto.StudentDto;
import uz.pdp.studentverify.model.Student;
import uz.pdp.studentverify.payload.ApiResponse;
import uz.pdp.studentverify.repository.StudentRepository;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EmailService emailService;

    public boolean existsById(UUID id){
        return studentRepository.existsById(id);
    }

    public boolean existsByIdAndCode(UUID id, String code){
        return studentRepository.existsById(id)&&studentRepository.existsByCode(code);
    }

    public boolean existsByEmail(String email){
        return studentRepository.existsByEmail(email);
    }


    public List<Student> getAllByActiveTrue(){
        return studentRepository.getAllByActiveTrue();
    }


    /** Saqlash va xabar yuborish **/
    public ApiResponse save(StudentDto studentDto, StringBuffer url){
        if (existsByEmail(studentDto.getEmail())){
            Student student=studentRepository.findByEmail(studentDto.getEmail());
            if (student.isActive()) return new ApiResponse("Bu email oldin ro'yhatdan o'tgan!",false);
            else return new ApiResponse("Bu emailga verifikatsiya kodi yuborilgan! Tasdiqlang!",false);
        } else {

            Student student=new Student();
            student.setActive(false);
            student.setEmail(studentDto.getEmail());
            student.setFirstname(studentDto.getFirstname());
            student.setLastname(studentDto.getLastname());
            student.setCode(CodeGenerator.generate());
            student.setId(UUID.randomUUID());
            ApiResponse apiResponse = emailService.sendCode(student,url);
            if (apiResponse.isSuccess()){
                student.setFullName(student.getFirstname()+" "+student.getLastname());
                studentRepository.save(student);
            }
            return apiResponse;
        }
    }



    /** Tasdiqlash **/
    public ApiResponse verify(UUID id,String code){
        if (existsByIdAndCode(id,code)){
            Student student=studentRepository.findById(id).get();
            if (student.isActive()){
                return new ApiResponse("Bu email allaqachon ro'yhatdan o'tgan!",false);
            }
            else {
                try {
                    student.setActive(true);
                    studentRepository.save(student);
                    emailService.sendCongrats(student);
                    return new ApiResponse("Muvaffaqiyatli ro'yhatdan o'tildi!",true);
                } catch (Exception e){
                    return new ApiResponse("Error",false);
                }

            }
        }
        return new ApiResponse("Error",false);

    }


    /** Tasdiqlashni rad etish  **/
    public ApiResponse cancel(UUID id,String code){
        if (existsByIdAndCode(id,code)){
            Student student=studentRepository.findById(id).get();
            if (student.isActive()){
                return new ApiResponse("Bu email allaqachon ro'yhatdan o'tgan!",false);
            }
            else {
                studentRepository.deleteById(id);
                return new ApiResponse("Bekor qilindi!",true);
            }
        }
        return new ApiResponse("Error",false);
    }

}
