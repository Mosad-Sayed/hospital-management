package com.example.hospitalmanagements.license;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CryptoUtil {

    private static final String SECRET_KEY =
            "HOSPITALKEY2026!"; // 16 chars

    public static String encrypt(String data) {
        try {
            SecretKeySpec key =
                new SecretKeySpec(SECRET_KEY.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            return Base64.getEncoder()
                .encodeToString(cipher.doFinal(data.getBytes()));

        } catch (Exception e) {
            e.printStackTrace(); // 👈 مهم جدًا
            return null;
        }
    }

    public static String decrypt(String encrypted) {
        try {
            SecretKeySpec key =
                new SecretKeySpec(SECRET_KEY.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);

            return new String(cipher.doFinal(
                Base64.getDecoder().decode(encrypted)));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
