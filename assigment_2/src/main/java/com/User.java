package com;

abstract class User{
    private final int id;
    private String name;
    private String email;

    public User(int id, String name, String email)
    {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public String getEmail() {return email;}

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }

    public abstract void displayInfo();

    @Override
    public String toString(){
        return "ID: " + id + ", Name: " + name + ", Email: " + email;
    }

}