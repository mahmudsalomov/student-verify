package uz.pdp.studentverify.component;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;

@Component
public final class CodeGenerator {

    /** 6 ta belgili kod generatsiya qiladigan metod **/
    public static String generate(){
        SecureRandom random = new SecureRandom();
        return new BigInteger(30, random).toString(32).toUpperCase();
    }

}
