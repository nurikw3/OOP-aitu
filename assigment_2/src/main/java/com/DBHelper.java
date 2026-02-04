package com;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DBHelper {

    private static final String URL = "jdbc:postgresql://localhost:5432/freelance_oop";
    private static final String USER = "nurasyk";
    private static final String PASSWORD = "12345";

    // ==================== CONNECTION ====================

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // ==================== JOBS CRUD ====================

    public static boolean saveJob(Job job)
    {
        String sql = "INSERT INTO jobs (id, title,budget,duration, city) VALUES (?,?,?,?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setInt(1, job.getJobId());
            ps.setString(2, job.getTitle());
            ps.setDouble(3, job.getBudget());
            ps.setString(4, job.getDuration());
            ps.setString(5, job.getSity());
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            System.err.println("Error in saving..");
            e.printStackTrace();
            return false;
        }
    }

    public static List<Job> getAllJobs() {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT id, title , budget , duration, city FROM jobs";
        try (
                Connection con = getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)
        )
        {
            while (rs.next()) {
                jobs.add(new Job(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getDouble("budget"),
                        rs.getString("duration"),
                        rs.getString("city"),
                        new ArrayList<>()
                ));
            }
        } catch (SQLException e) {
            System.err.println("error");
            e.printStackTrace();
        }
        return jobs;
    }

    public static Job getJobById(int id) {
        String sql = "SELECT id , title , budget, duration, city FROM jobs WHERE id = ?";
        try(
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        )
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                return new Job(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getDouble("budget"),
                        rs.getString("duration"),
                        rs.getString("city"),
                        new ArrayList<>()
                );
            }

        }
        catch (SQLException e)
        {
            System.err.println("Error in get job id");
            e.printStackTrace();
        }
        return null;
    }

    public static boolean updateJob(Job job) {
        String sql = "UPDATE jobs SET title = ?, budget = ?, duration = ? , city = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, job.getTitle());
            ps.setDouble(2, job.getBudget());
            ps.setString(3, job.getDuration());
            ps.setString(4, job.getSity());
            ps.setInt(5, job.getJobId());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error while updating Job id=" + job.getJobId());
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteJob(int id) {
        String sql = "DELETE FROM jobs WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error while deleting Job id=" + id);
            e.printStackTrace();
            return false;
        }
    }

    // ==================== FREELANCERS CRUD ====================

    public static boolean saveFreelancer(Freelancer f) {
        String sql = "INSERT INTO freelancers (id, name, email, hourly_rate, rating, skills) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, f.getId());
            ps.setString(2, f.getName());
            ps.setString(3, f.getEmail());
            ps.setDouble(4, f.getHourlyRate());
            ps.setDouble(5, f.getRating());

            // Convert skills list to comma-separated string
            String skillsStr = f.getSkills() != null ?
                    String.join(",", f.getSkills()) : "";
            ps.setString(6, skillsStr);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error while saving Freelancer");
            e.printStackTrace();
            return false;
        }
    }

    public static List<Freelancer> getAllFreelancers() {
        List<Freelancer> list = new ArrayList<>();
        String sql = "SELECT id, name, email, hourly_rate, rating, skills FROM freelancers";

        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                // Parse skills from comma-separated string
                String skillsStr = rs.getString("skills");
                List<String> skills = new ArrayList<>();
                if (skillsStr != null && !skillsStr.trim().isEmpty()) {
                    skills = Arrays.asList(skillsStr.split(","));
                }

                Freelancer f = new Freelancer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        skills,
                        rs.getDouble("hourly_rate")
                );
                f.setRating(rs.getDouble("rating"));
                list.add(f);
            }

        } catch (SQLException e) {
            System.err.println("Error while loading Freelancers");
            e.printStackTrace();
        }

        return list;
    }

    public static Freelancer getFreelancerById(int id) {
        String sql = "SELECT id, name, email, hourly_rate, rating, skills FROM freelancers WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Parse skills from comma-separated string
                String skillsStr = rs.getString("skills");
                List<String> skills = new ArrayList<>();
                if (skillsStr != null && !skillsStr.trim().isEmpty()) {
                    skills = Arrays.asList(skillsStr.split(","));
                }

                Freelancer f = new Freelancer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        skills,
                        rs.getDouble("hourly_rate")
                );
                f.setRating(rs.getDouble("rating"));
                return f;
            }

        } catch (SQLException e) {
            System.err.println("Error while loading Freelancer id=" + id);
            e.printStackTrace();
        }

        return null;
    }

    public static boolean updateFreelancer(Freelancer f) {
        String sql = "UPDATE freelancers SET name = ?, email = ?, hourly_rate = ?, rating = ?, skills = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, f.getName());
            ps.setString(2, f.getEmail());
            ps.setDouble(3, f.getHourlyRate());
            ps.setDouble(4, f.getRating());

            // Convert skills list to comma-separated string
            String skillsStr = f.getSkills() != null ?
                    String.join(",", f.getSkills()) : "";
            ps.setString(5, skillsStr);

            ps.setInt(6, f.getId());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error while updating Freelancer id=" + f.getId());
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteFreelancer(int id) {
        String sql = "DELETE FROM freelancers WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error while deleting Freelancer id=" + id);
            e.printStackTrace();
            return false;
        }
    }

    // ==================== CLIENTS CRUD ====================

    public static boolean saveClient(Client c) {
        String sql = "INSERT INTO clients (id, name, email, company) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.getId());
            ps.setString(2, c.getName());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getCompany());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error while saving Client");
            e.printStackTrace();
            return false;
        }
    }

    public static List<Client> getAllClients() {
        List<Client> list = new ArrayList<>();
        String sql = "SELECT id, name, email, company FROM clients";

        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Client(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("company")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error while loading Clients");
            e.printStackTrace();
        }

        return list;
    }

    public static Client getClientById(int id) {
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

        } catch (SQLException e) {
            System.err.println("Error while loading Client id=" + id);
            e.printStackTrace();
        }

        return null;
    }

    public static boolean updateClient(Client c) {
        String sql = "UPDATE clients SET name = ?, email = ?, company = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getName());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getCompany());
            ps.setInt(4, c.getId());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error while updating Client id=" + c.getId());
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteClient(int id) {
        String sql = "DELETE FROM clients WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error while deleting Client id=" + id);
            e.printStackTrace();
            return false;
        }
    }
}