import java.util.List;

public class Job {
    private final int jobId;
    private String title;
    private String description;
    private double budget;
    private String duration;
    private final List<String> requiredSkills;
    private String status;

    // Constructor
    public Job(int jobId, String title, String description, double budget, String duration, List<String> requiredSkills) {
        this.jobId = jobId;
        this.title = title;
        this.description = description;
        this.budget = budget;
        this.duration = duration;
        this.requiredSkills = requiredSkills;
        this.status = "Open";
    }

    // Getters
    public int getJobId() {
        return jobId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getBudget() {
        return budget;
    }

    public String getDuration() {
        return duration;
    }

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBudget(double budget) {
        if (budget > 0) this.budget = budget;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Methods
    public void displayJob() {
        System.out.println("\n--- Job Details ---");
        System.out.println("ID: " + jobId);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Budget: $" + budget);
        System.out.println("Duration: " + duration);
        System.out.println("Skills: " + String.join(", ", requiredSkills));
        System.out.println("Status: " + status);
    }

    public void closeJob() {
        this.status = "Closed";
        System.out.println("Job '" + title + "' has been closed.");
    }

    @Override
    public String toString() {
        return "Job(ID: " + jobId + ", Title: " + title + ", Budget: $" + budget + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        else if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        return false;
    }
}
