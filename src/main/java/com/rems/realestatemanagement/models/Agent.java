package com.rems.realestatemanagement.models;

public class Agent {
    private int id;
    private String agentName;
    private String email;
    private String passwordHash;

    // Constructor
    public Agent(int id, String agentName, String email, String passwordHash) {
        this.id = id;
        this.agentName = agentName;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getAgentName() { return agentName; }
    public void setAgentName(String agentName) { this.agentName = agentName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

}
