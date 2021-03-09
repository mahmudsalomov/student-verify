package uz.pdp.studentverify.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uz.pdp.studentverify.service.StudentService;



@org.springframework.stereotype.Controller
public class Controller {

    /** Asosiy sahifa yo'li **/
    @GetMapping("/")
    public String main(){
        return "main";
    }


    /** Tasdiqlash natijasining yo'li  **/
    @GetMapping("/result")
    public String result(){
        return "result";
    }

}
