import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("========== FREELANCE JOB PORTAL SYSTEM ==========\n");

        // Create com.JobPortal instance
        JobPortal portal = new JobPortal("FreelanceHub");

        // Create com.Job instances
        Job job1 = new Job(
                101,
                "Web Developer Needed",
                "Build a responsive e-commerce website",
                5000.0,
                "2 months",
                Arrays.asList("HTML", "CSS", "JavaScript", "React")
        );

        Job job2 = new Job(
                102,
                "Mobile App Development",
                "Create a fitness tracking mobile app",
                8000.0,
                "3 months",
                Arrays.asList("Java", "Android", "Firebase")
        );

        Job job3 = new Job(
                103,
                "Logo Design",
                "Design modern logo for tech startup",
                500.0,
                "1 week",
                Arrays.asList("Photoshop", "Illustrator", "Graphic Design")
        );

        // Create com.Freelancer instances
        Freelancer freelancer1 = new Freelancer(
                201,
                "Sheldon LiKuper",
                "sheldon@email.com",
                Arrays.asList("HTML", "CSS", "JavaScript", "React", "Node.js"),
                50.0,
                5
        );
        freelancer1.setRating(4.8);

        Freelancer freelancer2 = new Freelancer(
                202,
                "Richard Hendricks",
                "richard@email.com",
                Arrays.asList("Java", "Python", "Android", "Firebase"),
                45.0,
                3
        );
        freelancer2.setRating(4.5);

        Freelancer freelancer3 = new Freelancer(
                203,
                "Dana White",
                "danagivemechance@email.com",
                Arrays.asList("Photoshop", "Illustrator", "Graphic Design", "UI/UX"),
                40.0,
                4
        );
        freelancer3.setRating(4.9);

        // Add jobs and freelancers to portal
        portal.addJob(job1);
        portal.addJob(job2);
        portal.addJob(job3);

        portal.addFreelancer(freelancer1);
        portal.addFreelancer(freelancer2);
        portal.addFreelancer(freelancer3);

        // Display all jobs and freelancers
        portal.displayAllJobs();
        portal.displayAllFreelancers();

        // Display individual job and freelancer details
        job1.displayJob();
        freelancer1.displayFreelancer();

        // Match freelancers to jobs
        portal.matchFreelancerToJob(freelancer1, job1);
        portal.matchFreelancerToJob(freelancer2, job2);
        portal.matchFreelancerToJob(freelancer3, job3);
        portal.matchFreelancerToJob(freelancer1, job2); // Mismatch example

        // com.Test methods
        System.out.println("\n--- Testing Methods ---");
        freelancer1.addSkill("TypeScript");
        freelancer1.completeProject();
        job3.closeJob();

        // Compare objects
        System.out.println("\n--- Object Comparisons ---");
        System.out.println("job1 equals job2? " + job1.equals(job2));
        System.out.println("job1 equals job1? " + job1.equals(job1));
        System.out.println("freelancer1 equals freelancer2? " + freelancer1.equals(freelancer2));
        System.out.println("freelancer1 equals freelancer1? " + freelancer1.equals(freelancer1));

        // com.Test getters and setters
        System.out.println("\n--- Testing Getters/Setters ---");
        System.out.println("Job1 budget before: $" + job1.getBudget());
        job1.setBudget(5500.0);
        System.out.println("Job1 budget after: $" + job1.getBudget());

        System.out.println("Freelancer1 hourly rate before: $" + freelancer1.getHourlyRate());
        freelancer1.setHourlyRate(55.0);
        System.out.println("Freelancer1 hourly rate after: $" + freelancer1.getHourlyRate());

        // Display portal statistics
        portal.displayPortalStats();

        // Display toString() methods
        System.out.println("\n--- toString() Outputs ---");
        System.out.println(portal);
        System.out.println(job1);
        System.out.println(freelancer1);

        System.out.println("\n========== END OF PROGRAM ==========");
    }
}