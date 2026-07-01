package com.example.hospitalmanagements;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {

    public static void main(String[] args) {

        String url = "jdbc:mysql://srv1995.hstgr.io:3306/u948585946_evernote";
        String user = "u948585946_everroot";
        String pass = "Java@55555";

        int limit = 20;   // عدد الصفوف اللي عايز تعرضها

        String query = "SELECT diet_order, file_no, name_of_patient, notes, pattern_feeds FROM patient LIMIT ?";

        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, limit);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                String dietOrder = rs.getString("diet_order");
                String fileNo = rs.getString("file_no");
                String name = rs.getString("name_of_patient");
                String notes = rs.getString("notes");
                String patternFeeds = rs.getString("pattern_feeds");

                System.out.println(
                    "Diet Order: " + dietOrder +
                    " | File No: " + fileNo +
                    " | Name: " + name +
                    " | Notes: " + notes +
                    " | Pattern Feeds: " + patternFeeds
                );
            }

            conn.close();
            System.out.println("Done Fetching.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
