package com.example.hospitalmanagements.license;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Scanner;

public class DeviceInfo {

    public static String getMacAddress() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface ni = NetworkInterface.getByInetAddress(ip);
            byte[] mac = ni.getHardwareAddress();

            StringBuilder sb = new StringBuilder();
            for (byte b : mac) {
                sb.append(String.format("%02X-", b));
            }
            return sb.substring(0, sb.length() - 1);

        } catch (Exception e) {
            return "UNKNOWN";
        }
    }

    public static String getDiskSerial() {
        try {
            Process p = Runtime.getRuntime()
                    .exec("wmic diskdrive get serialnumber");
            Scanner sc = new Scanner(p.getInputStream());
            sc.next();
            return sc.next().trim();
        } catch (Exception e) {
            return "UNKNOWN";
        }
    }
}
