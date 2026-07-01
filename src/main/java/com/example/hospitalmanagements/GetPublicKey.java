package com.example.hospitalmanagements;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.cert.Certificate;
import java.util.Base64;

public class GetPublicKey {
    public static void main(String[] args) throws Exception {

        String filePath = "G:\\201_natrec_Crypto_Cer.pfx"; // غيّر لو CER
        String password = "Aa123456"; // مهم لو PFX

        X509Certificate cert;

        if (filePath.endsWith(".pfx") || filePath.endsWith(".p12")) {
            // ✅ قراءة PFX
            KeyStore keystore = KeyStore.getInstance("PKCS12");
            FileInputStream fis = new FileInputStream(filePath);
            keystore.load(fis, password.toCharArray());

            String alias = keystore.aliases().nextElement();
            Certificate certificate = keystore.getCertificate(alias);
            cert = (X509Certificate) certificate;

        } else {
            // ✅ قراءة CER
            FileInputStream fis = new FileInputStream(filePath);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            cert = (X509Certificate) cf.generateCertificate(fis);
        }

        // 🔥 استخراج الـ Public Key
        String pubKey = Base64.getMimeEncoder()
                .encodeToString(cert.getPublicKey().getEncoded());

        System.out.println("-----BEGIN PUBLIC KEY-----");
        System.out.println(pubKey);
        System.out.println("-----END PUBLIC KEY-----");
    }
}