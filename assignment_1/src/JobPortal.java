import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class JobPortal {
    private String portalName;
    private final List<Job> jobs;
    private final List<Freelancer> freelancers;
    private int totalTransactions;

    // Constructor
    public JobPortal(String portalName) {
        this.portalName = portalName;
        this.jobs = new ArrayList<>();
        this.freelancers = new ArrayList<>();
        this.totalTransactions = 0;
    }

    // Getters
    public String getPortalName() { return portalName; }
    public List<Job> getJobs() { return jobs; }
    public List<Freelancer> getFreelancers() { return freelancers; }
    public int getTotalTransactions() { return totalTransactions; }

    // Setters
    public void setPortalName(String portalName) { this.portalName = portalName; }

    // Methods
    public void addJob(Job job) {
        jobs.add(job);
        System.out.println("Job '" + job.getTitle() + "' added to portal.");
    }

    public void addFreelancer(Freelancer freelancer) {
        freelancers.add(freelancer);
        System.out.println("Freelancer '" + freelancer.getName() + "' registered.");
    }

    public void displayAllJobs() {
        System.out.println("\n=== All Jobs on " + portalName + " ===");
        for (Job job : jobs) {
            System.out.println(job);
        }
    }

    public void displayAllFreelancers() {
        System.out.println("\n=== All Freelancers on " + portalName + " ===");
        for (Freelancer freelancer : freelancers) {
            System.out.println(freelancer);
        }
    }

    public void matchFreelancerToJob(Freelancer freelancer, Job job) {
        System.out.println("\n--- Matching Process ---");
        boolean isMatch = false;
        for (String skill : job.getRequiredSkills()) {
            if (freelancer.hasSkill(skill)) {
                isMatch = true;
                break;
            }
        }

        if (isMatch) {
            System.out.println(freelancer.getName() + " is a good match for '" + job.getTitle() + "'!");
            totalTransactions++;
        } else {
            System.out.println(freelancer.getName() + " doesn't match the required skills for '" + job.getTitle() + "'");
        }
    }

    public void displayPortalStats() {
        System.out.println("\n=== Portal Statistics ===");
        System.out.println("Portal Name: " + portalName);
        System.out.println("Total Jobs: " + jobs.size());
        System.out.println("Total Freelancers: " + freelancers.size());
        System.out.println("Total Transactions: " + totalTransactions);
    }

    @Override
    public String toString() {
        return "JobPortal(Name: " + portalName + ", Jobs: " + jobs.size() + ", Freelancers: " + freelancers.size() + ")";
    }
}