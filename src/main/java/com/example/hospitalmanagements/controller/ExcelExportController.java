package com.example.hospitalmanagements.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.hospitalmanagements.entity.Patient;
import com.example.hospitalmanagements.repository.PatientRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ExcelExportController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=patients.xlsx");

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Patients");

            // إعداد الشعار
            try (InputStream logoStream = getClass().getResourceAsStream("/static/images/logo.png")) {
                if (logoStream != null) {
                    byte[] logoBytes = IOUtils.toByteArray(logoStream);
                    int pictureIdx = workbook.addPicture(logoBytes, Workbook.PICTURE_TYPE_PNG);
                    CreationHelper helper = workbook.getCreationHelper();
                    Drawing<?> drawing = sheet.createDrawingPatriarch();
                    ClientAnchor anchor = helper.createClientAnchor();
                    anchor.setCol1(0);
                    anchor.setRow1(0);
                    Picture picture = drawing.createPicture(anchor, pictureIdx);
                    picture.resize(2, 2);
                }
            }

            // إعداد الأنماط
            CellStyle headerStyle = createHeaderStyle(workbook);
            CellStyle subHeaderStyle = createSubHeaderStyle(workbook);

            // إعداد الترويسة
            setupHeaders(sheet, headerStyle, subHeaderStyle);

            // جلب البيانات
            List<Patient> patients = patientRepository.findAll();
            int rowNum = 2;

            for (Patient patient : patients) {
                Row row = sheet.createRow(rowNum++);
                populatePatientData(row, patient);
            }

            // إضافة الجزء السفلي (الإجمالي والتوقيعات)
            addFooter(sheet, rowNum, patients.size(), headerStyle);

            // ضبط عرض الأعمدة
            autoSizeColumns(sheet, 13);

            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new IOException("Error exporting data to Excel", e);
        }
    }

    private CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        return headerStyle;
    }

    private CellStyle createSubHeaderStyle(Workbook workbook) {
        CellStyle subHeaderStyle = workbook.createCellStyle();
        subHeaderStyle.cloneStyleFrom(createHeaderStyle(workbook));
        return subHeaderStyle;
    }

    private void setupHeaders(Sheet sheet, CellStyle headerStyle, CellStyle subHeaderStyle) {
        Row headerRow1 = sheet.createRow(0);
        Row headerRow2 = sheet.createRow(1);

        String[] mainHeaders = {"S.No", "File No", "NOFPATIENT", "AGE", "ROOM NO.", "BED NO.",
                "Pattern Feeds", "Diagnosis", "Diet order", "AdDate", "CoName"};
        String[] subHeaders = {"Nor.", "Chi.", "Spe."};

        int colIndex = 0;
        for (String header : mainHeaders) {
            if (header.equals("Pattern Feeds")) {
                sheet.addMergedRegion(new CellRangeAddress(0, 0, colIndex, colIndex + 2));
                Cell cell = headerRow1.createCell(colIndex);
                cell.setCellValue(header);
                cell.setCellStyle(headerStyle);

                for (String subHeader : subHeaders) {
                    Cell subCell = headerRow2.createCell(colIndex++);
                    subCell.setCellValue(subHeader);
                    subCell.setCellStyle(subHeaderStyle);
                }
            } else {
                sheet.addMergedRegion(new CellRangeAddress(0, 1, colIndex, colIndex));
                Cell cell = headerRow1.createCell(colIndex);
                cell.setCellValue(header);
                cell.setCellStyle(headerStyle);
                colIndex++;
            }
        }
    }

    private void populatePatientData(Row row, Patient patient) {
        row.createCell(0).setCellValue(row.getRowNum() - 1); // S.No
        row.createCell(1).setCellValue(patient.getFileNo());
        row.createCell(2).setCellValue(patient.getNameOfPatient());
        row.createCell(3).setCellValue(patient.getAge());
        row.createCell(4).setCellValue(patient.getRoom());
        row.createCell(5).setCellValue(patient.getBed());

        String patternFeeds = patient.getPatternFeeds();
        row.createCell(6).setCellValue("Nor.".equals(patternFeeds) ? "✔" : "");
        row.createCell(7).setCellValue("Chi.".equals(patternFeeds) ? "✔" : "");
        row.createCell(8).setCellValue("Spe.".equals(patternFeeds) ? "✔" : "");

        row.createCell(9).setCellValue(patient.getDiagnosis());
        row.createCell(10).setCellValue(patient.getDietOrder());
        row.createCell(11).setCellValue(patient.getAdmissionDate() != null ? patient.getAdmissionDate().toString() : "N/A");
        row.createCell(12).setCellValue(patient.getCompanionName());
    }

    private void addFooter(Sheet sheet, int rowNum, int totalPatients, CellStyle headerStyle) {
        int totalRowIndex = rowNum;
        Row totalRow = sheet.createRow(totalRowIndex);
        totalRow.createCell(0).setCellValue("TOTAL (PAT., COM.)");
        sheet.addMergedRegion(new CellRangeAddress(totalRowIndex, totalRowIndex, 0, 5));
        Cell totalCell = totalRow.createCell(6);
        totalCell.setCellValue(totalPatients);
        totalCell.setCellStyle(headerStyle);

        sheet.addMergedRegion(new CellRangeAddress(totalRowIndex, totalRowIndex, 6, 12));

        // إضافة التوقيعات
        String[] signHeaders = {"Nurse of ward", "Member from department of nutrition", "Hospital director"};
        int signRowIndex = totalRowIndex + 1;

        for (String header : signHeaders) {
            Row signRow = sheet.createRow(signRowIndex++);
            Cell nameCell = signRow.createCell(0);
            nameCell.setCellValue(header + "\nName:");
            sheet.addMergedRegion(new CellRangeAddress(signRowIndex - 1, signRowIndex - 1, 0, 3));
            Cell signCell = signRow.createCell(4);
            signCell.setCellValue("Sign:");
            sheet.addMergedRegion(new CellRangeAddress(signRowIndex - 1, signRowIndex - 1, 4, 12));
        }
    }

    private void autoSizeColumns(Sheet sheet, int numberOfColumns) {
        for (int i = 0; i < numberOfColumns; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}