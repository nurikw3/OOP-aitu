package src;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        JobPortal portal = new JobPortal("Aitu.net");


        System.out.println("[INFO] creating test data...");


        Freelancer elhan = new Freelancer(1, "Elhan VozduHan", "vozduhan@mail.ru",
                Arrays.asList("ProfessionalLie","Maybe Java", "Little bit Python", "Thinking jiu-jitsu is a city"), 50.0);
        elhan.setRating(0.1);


        Freelancer yerlen = new Freelancer(2, "Yerlen Yerlenovich", "yerlen@aitu.kz",
                Arrays.asList("Python", "SQL", "Pandas", "Numpy"), 60.0);
        yerlen.setRating(3.0);


        Freelancer angelinaJolie = new Freelancer(3, "Angelina Jolie", "whoknows@to4noNEaitu.kz",
                Arrays.asList("Python", "C++", "Java", "Pascal"), 65.0);
        angelinaJolie.setRating(4.0);


//        portal.addFreelancer(elhan);
//        portal.addFreelancer(yerlen);
//        portal.addFreelancer(angelinaJolie);
        List<Freelancer> rabotniki = new ArrayList<>(List.of(yerlen, elhan, angelinaJolie));
        rabotniki.forEach(r -> portal.addFreelancer(r));

        // clients
        Client c1 = new Client(101, "Tech Corp", "contact@techcorp.com", "Tech Corp Inc");
        Client c2 = new Client(102, "StartupXYZ", "hello@startupxyz.com", "StartupXYZ Ltd");

//        portal.addClient(c1);
//        portal.addClient(c2);

        List<Client> clienti = new ArrayList<>(List.of(c1,c2));
        clienti.forEach(portal::addClient);


        Job job1 = new Job(201, "Build Cat E-commerce Website", 5000.0, "2 months",
                Arrays.asList("JavaScript", "React", "Node.js"));

        Job job2 = new Job(202, "Mobile app for predict Hotdog / No Hotdog", 8000.0, "3 months",
                Arrays.asList("Java", "Android"));

        Job job3 = new Job(203, "ML: Create gpt from scratch in java", 3000.0, "1 month",
                Arrays.asList("Python", "SQL"));

        List.of(job1,job2,job3).forEach(portal::addJob);
        while (true) {
            System.out.println("\n========================================");
            System.out.println("     FREELANCE JOB PORTAL MENU");
            System.out.println("========================================");
            System.out.println("1. Display All Jobs");
            System.out.println("2. Display All Freelancers");
            System.out.println("3. Display All Clients");
            System.out.println("4. Filter Jobs by Budget");
            System.out.println("5. Filter Freelancers by Rate");
            System.out.println("6. Search Job by ID");
            System.out.println("7. Search Jobs by Title");
            System.out.println("8. Search Freelancers by Skill");
            System.out.println("9. Sort Jobs by Budget");
            System.out.println("10. Sort Freelancers by Rating");
            System.out.println("11. Demo: Polymorphism (displayInfo)");
            System.out.println("12. Demo: equals() and hashCode()");
            System.out.println("0. Exit");
            System.out.println("========================================");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    portal.displayAllJobs();
                    break;

                case 2:
                    portal.displayAllFreelancers();
                    break;

                case 3:
                    portal.displayAllClients();
                    break;

                case 4:
                    System.out.print("Enter minimum budget: $");
                    double minBudget = scanner.nextDouble();
                    System.out.print("Enter maximum budget: $");
                    double maxBudget = scanner.nextDouble();

                    List<Job> filteredJobs = portal.filterJobsByBudget(minBudget, maxBudget);
                    System.out.println("\n--- Filtered Jobs ($" + minBudget + " - $" + maxBudget + ") ---");
                    for (Job job : filteredJobs) {
                        System.out.println(job);
                    }
                    break;

                case 5:
                    System.out.print("Enter maximum hourly rate: $");
                    double maxRate = scanner.nextDouble();

                    List<Freelancer> filteredFreelancers = portal.filterFreelancerByRate(maxRate);
                    System.out.println("\n--- Freelancers with rate <= $" + maxRate + " ---");
                    for (Freelancer f : filteredFreelancers) {
                        System.out.println(f);
                    }
                    break;

                case 6:
                    System.out.print("Enter Job ID: ");
                    int jobId = scanner.nextInt();

                    Job foundJob = portal.searchJobByID(jobId);
                    if (foundJob != null) {
                        foundJob.displayJob();
                    } else {
                        System.out.println("Job not found!");
                    }
                    break;

                case 7:
                    System.out.print("Enter keyword to search in job titles: ");
                    String keyword = scanner.nextLine();

                    List<Job> searchResults = portal.searchJobByTitle(keyword);
                    System.out.println("\n--- Search Results for '" + keyword + "' ---");
                    for (Job job : searchResults) {
                        System.out.println(job);
                    }
                    break;

                case 8:
                    System.out.print("Enter skill to search: ");
                    String skill = scanner.nextLine();

                    List<Freelancer> skillResults = portal.searchFreelancerBySkill(skill);
                    System.out.println("\n--- Freelancers with skill: " + skill + " ---");
                    for (Freelancer f : skillResults) {
                        System.out.println(f);
                    }
                    break;

                case 9:
                    portal.sortByJobsBudget(true);
                    portal.displayAllJobs();
                    break;

                case 10:
                    portal.sortFreelancerByRating();
                    portal.displayAllFreelancers();
                    break;

                case 11:
                    portal.displayAllClients();
                    break;

                case 12:
                    System.out.println("\n========== EQUALS & HASHCODE DEMO ==========");

                    Freelancer testF1 = new Freelancer(999, "Test", "test@mail.com",
                            Arrays.asList("Java"), 50.0);
                    Freelancer testF2 = new Freelancer(999, "Different Name", "other@mail.com",
                            Arrays.asList("Python"), 100.0);

                    System.out.println("\nFreelancer 1: " + testF1);
                    System.out.println("Freelancer 2: " + testF2);
                    System.out.println("\nAre they equal? " + testF1.equals(testF2));
                    System.out.println("(Same ID = Same freelancer, even with different data)");
                    System.out.println("\nHashCode 1: " + testF1.hashCode());
                    System.out.println("HashCode 2: " + testF2.hashCode());
                    System.out.println("(Same hashCode because same ID)");
                    break;

                case 0:
                    System.out.println("\nThank you for using FreelanceHub!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }

    }
}
