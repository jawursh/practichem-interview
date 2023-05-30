package org.example;

public class LabRole {
    public Integer id;
    public Integer team;
    public Integer user;
    public Integer lab;
    public String role;

    public LabRole(Integer id, Integer user, Integer team, Integer lab, String role) {
        this.id = id;
        this.team = team;
        this.user = user;
        this.lab = lab;
        this.role = role;
    }
}