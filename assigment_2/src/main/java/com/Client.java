package com;

public class Client extends User{
    private final String company;
    private int postedJobs;


    public Client(int id, String name, String email, String company) {
        super(id, name, email);
        this.company = company;
        this.postedJobs = 0;
    }


    public String getCompany() { return company; }
    public int getPostedJobs() { return postedJobs; }


    public void incrementPostedJobs() {
        postedJobs++;
    }


    @Override
    public void displayInfo() {
        System.out.println("\n=== com.Client Info ===");
        System.out.println(super.toString());
        System.out.println("Company: " + company);
        System.out.println("Posted Jobs: " + postedJobs);
    }


    @Override
    public String toString() {
        return "com.Client{" + super.toString() + ", Company: " + company + "}";
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Client client = (Client) obj;
        return getId() == client.getId();
    }

}
