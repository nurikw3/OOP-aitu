package com;

import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== FREELANCE PORTAL ===");
            System.out.println("1. Show all");
            System.out.println("2. Find by ID");
            System.out.println("3. Add");
            System.out.println("4. Update");
            System.out.println("5. Delete");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 0) {
                System.out.println("Goodbye!");
                break;
            }

            System.out.println("Select type: 1-com.Job, 2-com.Freelancer, 3-com.Client");
            System.out.print("Type: ");
            int type = sc.nextInt();
            sc.nextLine();

            if (choice == 1) { // SHOW ALL
                if (type == 1) {
                    List<Job> jobs = DBHelper.getAllJobs();
                    for (Job j : jobs) {
                        System.out.println(j.getJobId() + " | " + j.getTitle() + " | $" + j.getBudget() + " | " + j.getSity());
                    }
                } else if (type == 2) {
                    List<Freelancer> frs = DBHelper.getAllFreelancers();
                    for (Freelancer f : frs) {
                        System.out.println(f.getId() + " | " + f.getName() + " | " + f.getEmail());
                    }
                } else if (type == 3) {
                    List<Client> cls = DBHelper.getAllClients();
                    for (Client c : cls) {
                        System.out.println(c.getId() + " | " + c.getName() + " | " + c.getCompany());
                    }
                }
            }

            else if (choice == 2) { // FIND BY ID
                System.out.print("ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                if (type == 1) {
                    Job j = DBHelper.getJobById(id);
                    if (j != null) {
                        System.out.println(j.getJobId() + " | " + j.getTitle() + " | $" + j.getBudget() + " | " + j.getDuration() + " | " + j.getSity());
                    } else {
                        System.out.println("Not found!");
                    }
                } else if (type == 2) {
                    Freelancer f = DBHelper.getFreelancerById(id);
                    if (f != null) {
                        System.out.println(f.getId() + " | " + f.getName() + " | " + f.getEmail() + " | $" + f.getHourlyRate());
                    } else {
                        System.out.println("Not found!");
                    }
                } else if (type == 3) {
                    Client c = DBHelper.getClientById(id);
                    if (c != null) {
                        System.out.println(c.getId() + " | " + c.getName() + " | " + c.getEmail() + " | " + c.getCompany());
                    } else {
                        System.out.println("Not found!");
                    }
                }
            }

            else if (choice == 3) { // ADD
                System.out.print("ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                boolean success = false;

                if (type == 1) {
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Budget: ");
                    double budget = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Duration: ");
                    String duration = sc.nextLine();
                    System.out.println("City: ");
                    String city = sc.nextLine();

                    Job j = new Job(id, title, budget, duration, city,null);
                    success = DBHelper.saveJob(j);

                } else if (type == 2) {
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Rate: ");
                    double rate = sc.nextDouble();
                    System.out.print("Rating: ");
                    double rating = sc.nextDouble();
                    sc.nextLine();

                    Freelancer f = new Freelancer(id, name, email, null, rate);
                    f.setRating(rating);
                    success = DBHelper.saveFreelancer(f);

                } else if (type == 3) {
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Company: ");
                    String company = sc.nextLine();

                    Client c = new Client(id, name, email, company);
                    success = DBHelper.saveClient(c);
                }

                System.out.println(success ? "Added!" : "Failed to add!");
            }

            else if (choice == 4) { // UPDATE
                System.out.print("ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                boolean success = false;

                if (type == 1) {
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Budget: ");
                    double budget = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Duration: ");
                    String duration = sc.nextLine();
                    System.out.println("City: ");
                    String city = sc.nextLine();

                    Job j = new Job(id, title, budget, duration, city,null);
                    success = DBHelper.updateJob(j);

                } else if (type == 2) {
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Rate: ");
                    double rate = sc.nextDouble();
                    System.out.print("Rating: ");
                    double rating = sc.nextDouble();
                    sc.nextLine();

                    Freelancer f = new Freelancer(id, name, email, null, rate);
                    f.setRating(rating);
                    success = DBHelper.updateFreelancer(f);

                } else if (type == 3) {
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Company: ");
                    String company = sc.nextLine();

                    Client c = new Client(id, name, email, company);
                    success = DBHelper.updateClient(c);
                }

                System.out.println(success ? "Updated!" : "Failed to update!");
            }

            else if (choice == 5) { // DELETE
                System.out.print("ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                boolean success = false;

                if (type == 1) {
                    success = DBHelper.deleteJob(id);
                } else if (type == 2) {
                    success = DBHelper.deleteFreelancer(id);
                } else if (type == 3) {
                    success = DBHelper.deleteClient(id);
                }

                System.out.println(success ? "Deleted!" : "Failed to delete!");
            }
        }

        sc.close();
    }
}
