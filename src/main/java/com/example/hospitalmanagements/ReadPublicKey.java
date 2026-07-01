package com.example.hospitalmanagements;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.cert.Certificate;

public class ReadPublicKey {
    public static void main(String[] args) {
        try {
            String pfxPath = "G:\\201_natrec_Crypto_Cer.pfx";
            String password = "Aa123456";

            // تحميل الـ KeyStore من نوع PKCS12
            KeyStore keystore = KeyStore.getInstance("PKCS12");
            FileInputStream fis = new FileInputStream(pfxPath);
            keystore.load(fis, password.toCharArray());

            // جلب أول alias (غالبًا واحد بس)
            String alias = keystore.aliases().nextElement();

            // استخراج الشهادة
            Certificate cert = keystore.getCertificate(alias);

            // استخراج الـ Public Key
            PublicKey publicKey = cert.getPublicKey();

            System.out.println("Public Key:");
            System.out.println(publicKey);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}