package uz.pdp.studentverify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.studentverify.dto.StudentDto;
import uz.pdp.studentverify.payload.ApiResponse;
import uz.pdp.studentverify.service.StudentService;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class EmailController {
    @Autowired
    private StudentService studentService;



    /**Kiritilgan emailga tasdiqlash xabari jo'natuvchi yo'l**/
    @PostMapping("/email")
    public HttpEntity<?> send(@RequestBody StudentDto studentDto, HttpServletRequest request){
        ApiResponse apiResponse=studentService.save(studentDto,request.getRequestURL());
        return ResponseEntity.ok(apiResponse);
    }


    /** Tasdiqlash yo'li, ModelAndView qaytaradi, html sahifa va model attributlari **/
    @GetMapping("/email/verify")
    public ModelAndView verify(@RequestParam UUID id,
                               @RequestParam String code,
                               @RequestParam boolean cancel){
        ApiResponse apiResponse;
        if (cancel){
            apiResponse=studentService.cancel(id,code);
        }
        else {
            apiResponse=studentService.verify(id,code);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("result");
        modelAndView.addObject("message",apiResponse.getMessage());
        return modelAndView;
    }

}
