package src;

import java.util.ArrayList;
import java.util.List;

public class JobPortal {
    private String portalName;
    private List<Job> jobs;
    private List<Freelancer> freelancers;
    private List<Client> clients;


    public JobPortal(String portalName) {
        this.portalName = portalName;
        this.jobs = new ArrayList<>();
        this.freelancers = new ArrayList<>();
        this.clients = new ArrayList<>();
    }


    public void addJob(Job job) {
        jobs.add(job);
        System.out.println("Job added " + job.getTitle());
    }


    public void addFreelancer(Freelancer freelancer) {
        freelancers.add(freelancer);
        System.out.println("Freelancer added " + freelancer.getName());
    }


    public void addClient(Client client) {
        clients.add(client);
        System.out.println("Freelancer added " + client.getName());
    }


    //Filtering
    public List<Job> filterJobsByBudget(double min, double max) {
        List<Job> filtered = new ArrayList<>();

        for (Job job : jobs) {
            if (job.getBudget() >= min && job.getBudget() <= max) filtered.add(job);
        }
        return filtered;
    }


    public List<Freelancer> filterFreelancerByRate(double mxRate) {
        List<Freelancer> filtered = new ArrayList<>();
        for (Freelancer freelancer : freelancers) {
            if (freelancer.getHourlyRate() <= mxRate) filtered.add(freelancer);
        }
        return filtered;
    }


    public List<Freelancer> filterFreelancerByRating(double mnRating) {

        List<Freelancer> filtered = new ArrayList<>();
        for (Freelancer freelancer : freelancers) {
            if (freelancer.getRating() >= mnRating) filtered.add(freelancer);
        }
        return filtered;
    }


    //search
    public Job searchJobByID(int jobID) {
        for (Job job : jobs) {
            if (job.getJobId() == jobID) return job;
        }
        return null;
    }


    public Freelancer searchFreelancerByID(int freelancerID) {
        for (Freelancer freelancer : freelancers) {
            if (freelancer.getId() == freelancerID) return freelancer;
        }
        return null;
    }


    public List<Job> searchJobByTitle(String title) {
        List<Job> res = new ArrayList<>();
        for (Job job : jobs) {
            if (job.getTitle().toLowerCase().contains(title.toLowerCase())) res.add(job);
        }
        return res;
    }


    public List<Freelancer> searchFreelancerBySkill(String skill) {
        List<Freelancer> res = new ArrayList<>();

        for (Freelancer freelancer : freelancers) {
            if (freelancer.hasSkill(skill)) res.add(freelancer);
        }
        return res;
    }


    //sort
    public void sortByJobsBudget(boolean reverse) {
        for (int i = 0; i < jobs.size(); i++) {
            for (int j = 0; j < jobs.size() - i -1; j++) {
                if (jobs.get(j).getBudget() < jobs.get(j+1).getBudget() && !reverse) {
                    Job temp = jobs.get(j);
                    jobs.set(j, jobs.get(j+1));
                    jobs.set(j+1, temp);
                }
                if (jobs.get(j).getBudget() > jobs.get(j+1).getBudget() && reverse) {
                    Job temp = jobs.get(j);
                    jobs.set(j, jobs.get(j+1));
                    jobs.set(j+1, temp);
                }
            }
        }
        System.out.println("Jobs budget was sorted | Reversed: " + reverse);
    }


    public void sortFreelancerByHR() {
        for (int i = 0; i < freelancers.size(); i++) {
            for (int j = 0; j < freelancers.size() - i - 1; j++) {
                if (freelancers.get(j).getHourlyRate() > freelancers.get(j + 1).getHourlyRate()) {
                    Freelancer temp = freelancers.get(j);
                    freelancers.set(j, freelancers.get(j + 1));
                    freelancers.set(j + 1, temp);
                }
            }
        }
        System.out.println("Freelancers was sorted by hourly rate");
    }


    public void sortFreelancerByRating() {
        for (int i = 0; i < freelancers.size(); i++) {
            for (int j = 0; j < freelancers.size() - i - 1; j++) {
                if (freelancers.get(j).getRating() > freelancers.get(j + 1).getRating()) {
                    Freelancer temp = freelancers.get(j);
                    freelancers.set(j, freelancers.get(j + 1));
                    freelancers.set(j + 1, temp);
                }
            }
        }
        System.out.println("Freelancers was sorted by rating");
    }


    public void displayAllJobs() {
        jobs.forEach(Job::displayJob);
    }


//    public void displayAllFreelancers() {
//        freelancers.forEach(f -> {
//            System.out.printf("%nID: %s, Name: %s, Rating: %s, Skills: %s",
//                    f.getId(),
//                    f.getName(),
//                    f.getInfoRating(),
//                    f.getSkills());
//        }
//        );
//    }
    public void displayAllFreelancers()
    {
        freelancers.forEach(f -> f.displayInfo());
    }


//    public void displayAllClients() {
//        clients.forEach(c -> {
//            System.out.printf("ID: %d, Company: %s, Jobs: %d",
//                    c.getId(),
//                    c.getCompany(),
//                    c.getPostedJobs());
//        }
//        );
//    }

    public void displayAllClients(){
        clients.forEach(Client::displayInfo);
    }
}