package com.project.doubleshop.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// 비밀번호 암호화 - SHA-256(복호화 불가능)
public class SHA256EncryptionUtil {

    public String encrypt(String str) {
        String sha = "";

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] inputBytes = str.getBytes();
            md.update(inputBytes);

            byte[] digested = md.digest(inputBytes);
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < digested.length; i++) {
                sb.append(Integer.toString((digested[i] & 0xff) + 0x100, 16).substring(1));
            }

            sha = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Encryption Exception", e);
        }

        return sha;
    }

}
