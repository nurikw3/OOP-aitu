package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Job {
    private final int jobId;
    private double budget;
    private String status;
    private String title;
    private final String duration;
    private final List<String> requiredSkills;

    public Job(int jobId, String title, double budget, String duration, List<String> requiredSkills) {
        this.jobId = jobId;
        this.title = title;
        this.budget = budget;
        this.duration = duration;
        this.requiredSkills = new ArrayList<>(requiredSkills);
        this.status = "Open";
    }

    public int getJobId() { return jobId; }
    public String getTitle() { return title; }
    public double getBudget() { return budget; }
    public String getDuration() { return duration; }
    public List<String> getRequiredSkills() { return requiredSkills; }
    public String getStatus() { return status; }

    public void setTitle(String title) { this.title = title; }
    public void setBudget(double budget) {
        if (budget > 0)
            this.budget = budget;
    }
    public void setStatus(String status) { this.status = status; }

    public void displayJob() {
        System.out.println("\n=== Job Details ===");
        System.out.println("ID: " + jobId);
        System.out.println("Title: " + title);
        System.out.println("Budget: $" + budget);
        System.out.println("Duration: " + duration);
        System.out.println("Required Skills: " + requiredSkills);
        System.out.println("Status: " + status);
    }

    @Override
    public String toString() {
        return "Job{ID: " + jobId + ", Title: " + title + ", Budget: $" + budget + ", Status: " + status + "}";
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Job other = (Job) obj;
        return getJobId() == other.getJobId();
    }
    @Override
    public int hashCode() {
        return Objects.hash(jobId);
    }
}
