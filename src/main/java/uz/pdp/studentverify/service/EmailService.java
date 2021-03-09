package uz.pdp.studentverify.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import uz.pdp.studentverify.model.Student;
import uz.pdp.studentverify.payload.ApiResponse;

import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private Configuration configuration;


    public ApiResponse sendCode(Student student, StringBuffer url){
        try {
            Map<String,Object> model=new HashMap<>();
            model.put("id",student.getId());
            model.put("email",student.getEmail());
            model.put("firstname",student.getFirstname());
            model.put("lastname",student.getLastname());
            model.put("code",student.getCode());
            model.put("url",url.toString());
            MimeMessage mimeMessage=sender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            Template template=configuration.getTemplate("email-template.ftl");
            String html= FreeMarkerTemplateUtils.processTemplateIntoString(template,model);
            helper.setTo(student.getEmail());
            helper.setSubject("Tasdiqlash!");
            helper.setText(html,true);
            sender.send(mimeMessage);
            return new ApiResponse("Tasdiqlash xabari yuborildi!",true);
        } catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("Error",false);
        }
    }


    /** Tabriklash xabari **/
    public void sendCongrats(Student student){
        try {
            MimeMessage mimeMessage=sender.createMimeMessage();
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(mimeMessage,true);
            helper.setTo(student.getEmail());
            helper.setSubject("Congrats!!!");
            helper.setText("Tabriklaymiz! "+student.getFullName()+" siz muvaffaqiyatli ro'yhatdan o'tdingiz!");
            String name="congrats.jpg";
            File file = ResourceUtils.getFile("src/main/resources/static/image/"+name);
            InputStream in = new FileInputStream(file);
            byte[] bdata = FileCopyUtils.copyToByteArray(in);
            ByteArrayDataSource attachment = new ByteArrayDataSource(bdata, "application/octet-stream");
            helper.addAttachment(name,attachment);
            sender.send(mimeMessage);
        }catch (Exception ignored){

        }
    }
}
