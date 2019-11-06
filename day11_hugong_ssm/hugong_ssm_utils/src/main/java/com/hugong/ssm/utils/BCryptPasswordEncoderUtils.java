package com.hugong.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//加密工具的测试类
public class BCryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String getBCryptPasswordEncoderUtils(String secret) {
        String encode = bCryptPasswordEncoder.encode(secret);
        return encode;
    }

    public static void main(String[] args) {
        String password = "123";
        String secret = getBCryptPasswordEncoderUtils(password);
        System.out.println(secret);
        //$2a$10$Hb5kjML2EG.ZmTEaGCl9Q.CVjiAlctaxNsyE.XmPDJTRqVVKWt1OW
        //$2a$10$9PGkyP30qgzXu4lAJBxSzupi1IF83oHyisM74DNt3.YEIBetkjjyO
    }
}
