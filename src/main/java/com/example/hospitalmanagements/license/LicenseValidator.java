package com.example.hospitalmanagements.license;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;

public class LicenseValidator {

    private static final String LICENSE_PATH =
            "C:/ProgramData/hospital/license.key";

    public static void check() {

        try {
            File file = new File(LICENSE_PATH);
            if (!file.exists()) {
                System.exit(0);
            }

            String encrypted = Files.readString(file.toPath());
            String decrypted = CryptoUtil.decrypt(encrypted);

            if (decrypted == null) {
                System.exit(0);
            }

            // format: mac|disk|expiry
            String[] parts = decrypted.split("\\|");

            String mac = parts[0];
            String disk = parts[1];
            LocalDate expiry = LocalDate.parse(parts[2]);

            if (!mac.equals(DeviceInfo.getMacAddress())) {
                System.exit(0);
            }

            if (!disk.equals(DeviceInfo.getDiskSerial())) {
                System.exit(0);
            }

            if (LocalDate.now().isAfter(expiry)) {
                System.exit(0);
            }

        } catch (Exception e) {
            System.exit(0);
        }
    }
}
