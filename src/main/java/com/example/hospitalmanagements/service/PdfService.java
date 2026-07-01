package com.example.hospitalmanagements.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.example.hospitalmanagements.entity.Patient;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PdfService {

    public ByteArrayInputStream generatePatientReport(List<Patient> patients) {
        Document document = new Document(PageSize.A4.rotate()); // Set page size to landscape
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, out);

            // Set text direction globally for the PDF
            writer.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);

            document.open();

            // Load Arabic font
            BaseFont bf = BaseFont.createFont("fonts/Rubik-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED); // Adjust as per your font file
            Font arabicFont = new Font(bf, 10); // Set font size to 10 for Arabic text

            // Add logo to the header
            ClassPathResource resource = new ClassPathResource("static/logo.png"); // Adjust path as per your project structure
            Image logo = Image.getInstance(resource.getURL());
            logo.scaleToFit(115, 115); // Increase size as needed
            logo.setAbsolutePosition(document.getPageSize().getWidth() - 150, document.getPageSize().getHeight() - 100); // Adjust coordinates as needed
            document.add(logo);

            // Add "Patient Report" below the header
            Font reportLabelFont = new Font(bf, 12, Font.BOLD); // Adjust font size and style
            Paragraph reportLabel = new Paragraph("Patient Report", reportLabelFont);
            reportLabel.setAlignment(Element.ALIGN_CENTER);
            reportLabel.setSpacingBefore(20); // Add space before the paragraph
            document.add(reportLabel);
            document.add(Chunk.NEWLINE);

            // Table
            PdfPTable table = new PdfPTable(10); // Adjust number of columns as needed
            table.setWidthPercentage(100);
            table.setWidths(new int[]{2, 3, 1, 1, 1, 2, 2, 2, 2, 1}); // Adjust column widths as needed

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            headFont.setSize(8); // Set font size to 8 for header cells
            PdfPCell cell;

            // Add table headers with bottom border
            cell = new PdfPCell(new Phrase("File No", headFont));
            cell.setBorder(Rectangle.BOTTOM); // Set only bottom border
            cell.setPadding(5); // Set padding
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Name", headFont));
            cell.setBorder(Rectangle.BOTTOM); // Set only bottom border
            cell.setPadding(5); // Set padding
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Age", headFont));
            cell.setBorder(Rectangle.BOTTOM); // Set only bottom border
            cell.setPadding(5); // Set padding
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Room", headFont));
            cell.setBorder(Rectangle.BOTTOM); // Set only bottom border
            cell.setPadding(5); // Set padding
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Bed", headFont));
            cell.setBorder(Rectangle.BOTTOM); // Set only bottom border
            cell.setPadding(5); // Set padding
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Diagnosis", headFont));
            cell.setBorder(Rectangle.BOTTOM); // Set only bottom border
            cell.setPadding(5); // Set padding
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Diet Order", headFont));
            cell.setBorder(Rectangle.BOTTOM); // Set only bottom border
            cell.setPadding(5); // Set padding
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Admission Date", headFont));
            cell.setBorder(Rectangle.BOTTOM); // Set only bottom border
            cell.setPadding(5); // Set padding
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Companion Name", headFont));
            cell.setBorder(Rectangle.BOTTOM); // Set only bottom border
            cell.setPadding(5); // Set padding
            table.addCell(cell);

            // Add Feeds header cell
            cell = new PdfPCell(new Phrase("Feeds", headFont));
            cell.setBorder(Rectangle.BOTTOM); // Set only bottom border
            cell.setPadding(5); // Set padding
            table.addCell(cell);

            // Add patient data
            Font dataFont = new Font(bf, 9); // Set font size to 9 for data cells
            for (Patient patient : patients) {
                table.addCell(new Phrase(patient.getFileNo(), dataFont));
                table.addCell(new Phrase(patient.getNameOfPatient(), dataFont));
                table.addCell(new Phrase(String.valueOf(patient.getAge()), dataFont));
                table.addCell(new Phrase(patient.getRoom(), dataFont));
                table.addCell(new Phrase(patient.getBed(), dataFont));
                table.addCell(new Phrase(patient.getDiagnosis(), dataFont));
                table.addCell(new Phrase(patient.getDietOrder(), dataFont));
                table.addCell(new Phrase(patient.getAdmissionDate(), dataFont));
                table.addCell(new Phrase(patient.getCompanionName(), dataFont));

                // Create nested table for Feeds
                PdfPTable feedsTable = new PdfPTable(3); // 3 columns for Feeds
                feedsTable.setWidthPercentage(100);
                feedsTable.setWidths(new int[]{1, 1, 1}); // Equal widths for the three cells

                // Add Feeds data
                PdfPCell feedsCellN = new PdfPCell();
                feedsCellN.setBorder(Rectangle.NO_BORDER); // No border for inner cell
                feedsCellN.setPadding(2); // Set padding
                feedsCellN.setHorizontalAlignment(Element.ALIGN_CENTER); // Align content horizontally
                if ("N".equals(patient.getPatternFeeds())) {
                    feedsCellN.addElement(new Chunk("X", dataFont)); // Add 'X' if Feeds is 'N'
                }
                feedsTable.addCell(feedsCellN);

                PdfPCell feedsCellC = new PdfPCell();
                feedsCellC.setBorder(Rectangle.NO_BORDER); // No border for inner cell
                feedsCellC.setPadding(2); // Set padding
                feedsCellC.setHorizontalAlignment(Element.ALIGN_CENTER); // Align content horizontally
                if ("C".equals(patient.getPatternFeeds())) {
                    feedsCellC.addElement(new Chunk("X", dataFont)); // Add 'X' if Feeds is 'C'
                }
                feedsTable.addCell(feedsCellC);

                PdfPCell feedsCellS = new PdfPCell();
                feedsCellS.setBorder(Rectangle.NO_BORDER); // No border for inner cell
                feedsCellS.setPadding(2); // Set padding
                feedsCellS.setHorizontalAlignment(Element.ALIGN_CENTER); // Align content horizontally
                if ("S".equals(patient.getPatternFeeds())) {
                    feedsCellS.addElement(new Chunk("X", dataFont)); // Add 'X' if Feeds is 'S'
                }
                feedsTable.addCell(feedsCellS);

                // Add nested table to main Feeds cell
                PdfPCell feedsMainCell = new PdfPCell(feedsTable);
                feedsMainCell.setBorder(Rectangle.BOTTOM); // Set only bottom border
                feedsMainCell.setPadding(5); // Set padding
                feedsMainCell.setVerticalAlignment(Element.ALIGN_MIDDLE); // Align content vertically

                table.addCell(feedsMainCell); // Add main Feeds cell to the main table
            }

            document.add(table);

            document.close();
            writer.close();

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
