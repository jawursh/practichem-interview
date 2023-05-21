package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.example.Membershipandroles.*;

public class UserLabRoles {

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

    /**
     * TODO: BONUS method
     */

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