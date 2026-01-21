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

    // ==================== JOBS CRUD ====================

    // CREATE
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

    // READ ALL
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

    // READ BY ID
    public static Job getJobById(int id) throws SQLException {
        String sql = "SELECT id, title, budget, duration FROM jobs WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Job(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getDouble("budget"),
                        rs.getString("duration"),
                        new ArrayList<>()
                );
            }
        }
        return null;
    }

    // UPDATE
    public static void updateJob(Job job) throws SQLException {
        String sql = "UPDATE jobs SET title = ?, budget = ?, duration = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, job.getTitle());
            ps.setDouble(2, job.getBudget());
            ps.setString(3, job.getDuration());
            ps.setInt(4, job.getJobId());
            ps.executeUpdate();
        }
    }

    // DELETE
    public static void deleteJob(int id) throws SQLException {
        String sql = "DELETE FROM jobs WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // ==================== FREELANCERS CRUD ====================

    // CREATE
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

    // READ ALL
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

    // READ BY ID
    public static Freelancer getFreelancerById(int id) throws SQLException {
        String sql = "SELECT id, name, email, hourly_rate, rating FROM freelancers WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Freelancer f = new Freelancer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        new ArrayList<>(),
                        rs.getDouble("hourly_rate")
                );
                f.setRating(rs.getDouble("rating"));
                return f;
            }
        }
        return null;
    }

    // UPDATE
    public static void updateFreelancer(Freelancer f) throws SQLException {
        String sql = "UPDATE freelancers SET name = ?, email = ?, hourly_rate = ?, rating = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, f.getName());
            ps.setString(2, f.getEmail());
            ps.setDouble(3, f.getHourlyRate());
            ps.setDouble(4, f.getRating());
            ps.setInt(5, f.getId());
            ps.executeUpdate();
        }
    }

    // DELETE
    public static void deleteFreelancer(int id) throws SQLException {
        String sql = "DELETE FROM freelancers WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // ==================== CLIENTS CRUD ====================

    // CREATE
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

    // READ ALL
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

    // READ BY ID
    public static Client getClientById(int id) throws SQLException {
        String sql = "SELECT id, name, email, company FROM clients WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Client(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("company")
                );
            }
        }
        return null;
    }

    // UPDATE
    public static void updateClient(Client c) throws SQLException {
        String sql = "UPDATE clients SET name = ?, email = ?, company = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getName());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getCompany());
            ps.setInt(4, c.getId());
            ps.executeUpdate();
        }
    }

    // DELETE
    public static void deleteClient(int id) throws SQLException {
        String sql = "DELETE FROM clients WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}