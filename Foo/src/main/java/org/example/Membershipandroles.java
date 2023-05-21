package org.example;

import java.util.*;
import java.util.stream.*;

public class Membershipandroles {
    // Data classes. Please do not change this code.
    public static class User {
        /**
         * really need accessors on this class to be able to filter by user ID         * @Data with lombok could help here :)
         */
        public Integer id;
        public String name;

        public User(Integer id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Team {
        public Integer id;
        public String name;

        public Team(Integer id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Lab {
        public Integer id;
        public String name;

        public Lab(Integer id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class UserTeam {
        public Integer id;
        public Integer user;
        public Integer team;

        public UserTeam(Integer id, Integer user, Integer team) {
            this.id = id;
            this.user = user;
            this.team = team;
        }
    }

    public static class LabRole {
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
    public static class UserLabRoles {

        public Integer userId;

        /**
         * setting the user on init saves potential db/network bandwidth
         */
        public UserLabRoles(Integer userId) {

            this.userId = userId;
        }

        public User getUser() {
            return USERS.stream()
                    .filter((user) -> user.id.equals(userId))
                    .collect(toSingleton());
        }

        public List<Lab> getLabs() {
            ArrayList<Lab> labs = new ArrayList<Lab>();

            List<LabRole> roles = LAB_ROLES.stream()
                    .filter((role) -> role.user.equals(this.userId))
                    .collect(Collectors.toList());


            for (LabRole role : roles) {
                Lab lab = LABS.stream()
                        .filter((theLab) -> theLab.id.equals(role.lab))
                        .collect(toSingleton());
                labs.add(lab);
            }
            // sort the list
            return labs.stream().sorted().collect(Collectors.toList());
        }

        public List<LabRole> getLabRoles(Integer labId) {
            List<LabRole> roles = LAB_ROLES.stream()
                    .filter((role) -> role.user.equals(this.userId))
                    .collect(Collectors.toList());
            return roles;
        }

        /**
         * OWNER - 1         * ADMIN - 2         * MEMBER - 3         * GUEST - 4         *         * NOTE: if user has both ADMIN AND GUEST, ADMIN takes priority
         */
        public String getHighestLabRole(Integer labId) {
            ArrayList<String> roleStrings = new ArrayList<>();
            for (LabRole role : getLabRoles(labId)) {
                roleStrings.add(role.role);
            }
            if (roleStrings.contains("ADMIN")) {
                return "ADMIN";
            }
            if (roleStrings.contains("OWNER")) {
                return "OWNER";
            }
            if (roleStrings.contains("MEMBER")) {
                return "MEMBER";
            }
            if (roleStrings.contains("GUEST")) {
                return "GUEST";
            } else {
                return "";
            }
        }

        // Bonus method
        // Only implement hasLabRole if time permits.        public Boolean hasLabRole(Integer labId, String role) {
        ArrayList<String> roleStrings = new ArrayList<>();
            for(
        LabRole labRole :

        getLabRoles(labId))

        {
            roleStrings.add(labRole.role);
        }
            if(roleStrings.contains(role))

        {
            return true;
        } else

        {
            return false;
        }
    }


    /**
     * util function for grabbing 1 item out of a list
     */
    public static <T> Collector<T, ?, T> toSingleton() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() != 1) {
                        throw new IllegalStateException();
                    }
                    return list.get(0);
                }
        );
    }
}