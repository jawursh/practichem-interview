package org.example;

import java.util.*;
import java.util.stream.*;

public class Membershipandroles {
    // Example Data. Please do not change this code.
    public static List<User> USERS = Arrays.asList(
            new User(1, "John Doe")
    );

    public static List<Team> TEAMS = Arrays.asList(
            new Team(1, "Curiosity"),
            new Team(2, "Perseverance")
    );

    public static List<Lab> LABS = Arrays.asList(
            new Lab(1, "NASA"),
            new Lab(2, "JPL"),
            new Lab(3, "SpaceX"),
            new Lab(4, "Blue Origin")
    );

    public static List<UserTeam> USER_TEAMS = Arrays.asList(
            new UserTeam(1, 1, 2)
    );

    public static List<LabRole> LAB_ROLES = Arrays.asList(
            new LabRole(1, 1, null, 1, "OWNER"),
            new LabRole(2, 1, null, 2, "MEMBER"),
            new LabRole(3, null, 1, 2, "GUEST"),
            new LabRole(4, null, 1, 3, "MEMBER"),
            new LabRole(5, null, 2, 1, "ADMIN"),
            new LabRole(6, null, 2, 2, "ADMIN"),
            new LabRole(7, null, 2, 4, "GUEST")
    );

    // Implement this class without changing the function prototypes.

}