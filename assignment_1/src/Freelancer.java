import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Freelancer {
    private final int freelancerId;
    private String name;
    private String email;
    private final List<String> skills;
    private double hourlyRate;
    private final int experienceYears;
    private double rating;
    private int completedProjects;

    // Constructor
    public Freelancer(int freelancerId, String name, String email, List<String> skills, double hourlyRate, int experienceYears) {
        this.freelancerId = freelancerId;
        this.name = name;
        this.email = email;
        this.skills = new ArrayList<>(skills); // Create mutable ArrayList
        this.hourlyRate = hourlyRate;
        this.experienceYears = experienceYears;
        this.rating = 0.0;
        this.completedProjects = 0;
    }

    // Getters
    public int getFreelancerId() { return freelancerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<String> getSkills() { return skills; }
    public double getHourlyRate() { return hourlyRate; }
    public int getExperienceYears() { return experienceYears; }
    public double getRating() { return rating; }
    public int getCompletedProjects() { return completedProjects; }

    // Setters
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format: " + email);
        }
        this.email = email;
    }

    public void setHourlyRate(double hourlyRate) {
        if (hourlyRate <= 0) {
            throw new IllegalArgumentException("Hourly rate must be positive, got: " + hourlyRate);
        }
        this.hourlyRate = hourlyRate;
    }

    public void setRating(double rating) {
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5, got: " + rating);
        }
        this.rating = rating;
    }

    // Methods
    public void displayFreelancer() {
        System.out.println("\n--- Freelancer Profile ---");
        System.out.println("ID: " + freelancerId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Skills: " + String.join(", ", skills));
        System.out.println("Hourly Rate: $" + hourlyRate);
        System.out.println("Experience: " + experienceYears + " years");
        System.out.println("Rating: " + rating + "/5.0");
        System.out.println("Completed Projects: " + completedProjects);
    }

    public void addSkill(String skill) {
        skills.add(skill);
        System.out.println("Skill '" + skill + "' added to profile.");
    }

    public void completeProject() {
        completedProjects++;
        System.out.println("Project completed! Total: " + completedProjects);
    }

    public boolean hasSkill(String skill) {
        return skills.contains(skill);
    }

    @Override
    public String toString() {
        return "Freelancer(ID: " + freelancerId + ", Name: " + name + ", Rate: $" + hourlyRate + "/hr)";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Freelancer freelancer = (Freelancer) obj;
        return freelancerId == freelancer.freelancerId;
    }
}