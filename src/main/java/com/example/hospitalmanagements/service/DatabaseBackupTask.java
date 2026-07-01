package com.example.hospitalmanagements.service;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DatabaseBackupTask {

    private static final String BACKUP_FOLDER_PATH = "D:\\hospital_management_backup/"; // المسار المناسب لحفظ النسخ الاحتياطية
    private static final String DATABASE_NAME = "hospital_management";
    private static final String CONFIG_FILE_PATH = "D:\\my.cnf"; // مسار ملف التكوين

	@Scheduled(fixedRate = 86400 * 1000)
    public void performBackup() {
        System.out.println("Starting backup process..."); // رسالة تسجيل
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String backupFilePath = BACKUP_FOLDER_PATH + DATABASE_NAME + "_" + timestamp + ".sql";

        String command = String.format("mysqldump --defaults-file=%s %s -r %s",
                CONFIG_FILE_PATH, DATABASE_NAME, backupFilePath);

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            int processComplete = process.waitFor();

            if (processComplete == 0) {
                System.out.println("Backup created successfully: " + backupFilePath);
            } else {
                System.out.println("Backup failed.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
