package com;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Objects;

public class Freelancer extends User{

    private final List<String> skills;
    private double hourlyRate;
    private double rating;


    public Freelancer(int id, String name, String email, List<String> skills, double hourlyRate) {
        super(id, name, email);
        this.skills = skills;
        this.hourlyRate = hourlyRate;
    }


    public List<String> getSkills() {return skills;}
    public double getHourlyRate() {return hourlyRate;}
    public double getRating() {return rating;}


    public void setHourlyRate(double hourlyRate) {if (hourlyRate > 0) this.hourlyRate = hourlyRate;}
    public void setRating(double rating) {if (rating > 0 && rating <= 5 ) this.rating = rating;}


    public void addSkill(String skill)
    {
        if (!skills.contains(skill)) skills.add(skill);
    }


    public boolean hasSkill(String skill)
    {
        for (String s: skills)
        {
            if (s.equalsIgnoreCase(skill)) return true;
        }
        return false;
    }
    @JsonIgnore
    public String getInfoRating() {
        return getRating() == 0.0 ? "No rating" : rating + "/5.0";
    }

    @Override
    public void displayInfo() {
        System.out.println("\n=== com.Freelancer Info ===");
        System.out.println(super.toString());
        System.out.println("Skills: " + skills);
        System.out.println("Hourly Rate: $" + hourlyRate);
//        System.out.println("Rating: " + rating + "/5.0");
        System.out.println("Rating: " + getInfoRating());
    }

    @Override
    public String toString() {
        return "com.Freelancer{" + super.toString() + ", Rate: $" + hourlyRate + ", Rating: " + getInfoRating() + "}";
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Freelancer other = (Freelancer) obj;
        return getId() == other.getId();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId());
    }

}
