package src;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    private static final String URL = "jdbc:postgresql://localhost:5432/freelance_oop";
    private static final String USER = "nurasyk";
    private static final String PASSWORD = "12345";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Jobs
    public static List<Job> getAllJobs() throws SQLException {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT id, title, budget, duration FROM jobs";
        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                double budget = rs.getDouble("budget");
                String duration = rs.getString("duration");
                jobs.add(new Job(id, title, budget, duration, new ArrayList<>()));
            }
        }
        return jobs;
    }

    public static void saveJob(Job job) throws SQLException {
        String sql = "INSERT INTO jobs (id, title, budget, duration) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, job.getJobId());
            ps.setString(2, job.getTitle());
            ps.setDouble(3, job.getBudget());
            ps.setString(4, job.getDuration());
            ps.executeUpdate();
        }
    }

    // Freelancers
    public static List<Freelancer> getAllFreelancers() throws SQLException {
        List<Freelancer> freelancers = new ArrayList<>();
        String sql = "SELECT id, name, email, hourly_rate, rating FROM freelancers";
        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                double rate = rs.getDouble("hourly_rate");
                double rating = rs.getDouble("rating");
                Freelancer f = new Freelancer(id, name, email, new ArrayList<>(), rate);
                f.setRating(rating);
                freelancers.add(f);
            }
        }
        return freelancers;
    }

    public static void saveFreelancer(Freelancer f) throws SQLException {
        String sql = "INSERT INTO freelancers (id, name, email, hourly_rate, rating) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, f.getId());
            ps.setString(2, f.getName());
            ps.setString(3, f.getEmail());
            ps.setDouble(4, f.getHourlyRate());
            ps.setDouble(5, f.getRating());
            ps.executeUpdate();
        }
    }

    //Clients
    public static List<Client> getAllClients() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT id, name, email, company FROM clients";
        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String company = rs.getString("company");
                clients.add(new Client(id, name, email, company));
            }
        }
        return clients;
    }

    public static void saveClient(Client c) throws SQLException {
        String sql = "INSERT INTO clients (id, name, email, company) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, c.getId());
            ps.setString(2, c.getName());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getCompany());
            ps.executeUpdate();
        }
    }
}
