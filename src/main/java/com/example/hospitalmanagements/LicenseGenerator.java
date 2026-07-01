package com.example.hospitalmanagements;

import com.example.hospitalmanagements.license.CryptoUtil;
import com.example.hospitalmanagements.license.DeviceInfo;

public class LicenseGenerator {

    public static void main(String[] args) {

        String mac = DeviceInfo.getMacAddress();
        String disk = DeviceInfo.getDiskSerial();
        String expiry = "2026-12-31";

        String raw = mac + "|" + disk + "|" + expiry;

        System.out.println(CryptoUtil.encrypt(raw));
    }
}
