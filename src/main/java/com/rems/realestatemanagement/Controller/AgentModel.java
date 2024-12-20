package com.rems.realestatemanagement.Controller;

import java.util.ArrayList;
import java.util.List;

public class AgentModel {


    public static class Agent {
        private int id;
        private String agentName;
        private String email;
        private String passwordHash;


        public Agent(int id, String agentName, String email, String passwordHash) {
            this.id = id;
            this.agentName = agentName;
            this.email = email;
            this.passwordHash = passwordHash;
        }

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getAgentName() { return agentName; }
        public void setAgentName(String agentName) { this.agentName = agentName; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPasswordHash() { return passwordHash; }
        public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    }

    private final List<Agent> agents;

    public AgentModel() {
        agents = new ArrayList<>();
    }

    public void addAgent(Agent agent) {
        agents.add(agent);
    }

    public List<Agent> getAgents() {
        return new ArrayList<>(agents);
    }

    public void removeAgent(Agent agent) {
        agents.remove(agent);
    }

    public void updateAgent(Agent updatedAgent) {
        for (int i = 0; i < agents.size(); i++) {
            if (agents.get(i).getId() == updatedAgent.getId()) {
                agents.set(i, updatedAgent);
                break;
            }
        }
    }
}