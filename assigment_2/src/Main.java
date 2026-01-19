package src;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JobPortal portal = new JobPortal("Aitu.net");

        try {
            DBHelper.getAllJobs().forEach(portal::addJob);
            DBHelper.getAllFreelancers().forEach(portal::addFreelancer);
            DBHelper.getAllClients().forEach(portal::addClient);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        while (true) {
            System.out.println("\n==================== FREELANCE PORTAL ====================");
            System.out.println("1. Display Jobs / Freelancers / Clients (choose columns)");
            System.out.println("2. Run custom SQL SELECT");
            System.out.println("0. Exit");
            System.out.println("==========================================================");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Choose type: 1-Jobs, 2-Freelancers, 3-Clients");
                    int type = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter columns to display (comma-separated, e.g., id,title,budget): ");
                    String cols = scanner.nextLine();

                    try (Connection conn = DBHelper.getConnection();
                         Statement st = conn.createStatement()) {

                        String table = switch (type) {
                            case 1 -> "jobs";
                            case 2 -> "freelancers";
                            case 3 -> "clients";
                            default -> "";
                        };

                        if (table.isEmpty()) {
                            System.out.println("Invalid type!");
                            break;
                        }

                        String sql = "SELECT " + cols + " FROM " + table;
                        ResultSet rs = st.executeQuery(sql);

                        ResultSetMetaData meta = rs.getMetaData();
                        int columnCount = meta.getColumnCount();

                        while (rs.next()) {
                            for (int i = 1; i <= columnCount; i++) {
                                System.out.print(meta.getColumnName(i) + ": " + rs.getString(i) + " | ");
                            }
                            System.out.println();
                        }

                    } catch (SQLException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Enter SQL SELECT query: ");
                    String sqlQuery = scanner.nextLine();

                    try (Connection conn = DBHelper.getConnection();
                         Statement st = conn.createStatement();
                         ResultSet rs = st.executeQuery(sqlQuery)) {

                        ResultSetMetaData meta = rs.getMetaData();
                        int columnCount = meta.getColumnCount();

                        while (rs.next()) {
                            for (int i = 1; i <= columnCount; i++) {
                                System.out.print(meta.getColumnName(i) + ": " + rs.getString(i) + " | ");
                            }
                            System.out.println();
                        }

                    } catch (SQLException e) {
                        System.out.println("SQL Error: " + e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Exiting portal. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
