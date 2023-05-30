package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserLabRolesTest {

    @Test
    public void getUserShouldReturnUserObject() {
        UserLabRoles userLabRoles = new UserLabRoles(1);
        User expectedUser = new User(1, "joe");
        User actualUser = userLabRoles.getUser();
        assertEquals(expectedUser.id, actualUser.id);
    }

    @Test
    public void getLabsShouldReturnOrderedArray() {
        UserLabRoles userLabRoles = new UserLabRoles(1);

        Lab expectedLab1 = new Lab(4, "Blue Origin");
        Lab expectedLab2 = new Lab(2, "JPL");
        Lab expectedLab3 = new Lab(1, "NASA");
        List<Lab> actualLabs = userLabRoles.getLabs();
        assertEquals(actualLabs.size(), 3);

    }

    @Test
    public void getLabRolesShouldReturnOrderedArray() {
        UserLabRoles userLabRoles = new UserLabRoles(1);

        LabRole expectedLabRole1 = new LabRole(1, 1, null, 1, "OWNER");
        LabRole expectedLabRole2 = new LabRole(2, 1, null, 2, "MEMBER");

        List<LabRole> actualLabRoles = userLabRoles.getLabRoles(1);
        assertEquals(actualLabRoles.size(), 2);
        /**
         * TODO: assert equals on the objects vs the fields
         */
        assertEquals(actualLabRoles.get(0).id, expectedLabRole1.id);
        assertEquals(actualLabRoles.get(1).id, expectedLabRole2.id);
        assertEquals(actualLabRoles.get(0).role, expectedLabRole1.role);
        assertEquals(actualLabRoles.get(1).role, expectedLabRole2.role);
    }

    @Test
    public void getHighestLabRoleShouldReturnDependingOnUser() {
        /**
         * USER 1 should be an owner
         */
        UserLabRoles userLabRoles = new UserLabRoles(1);
        LabRole expectedLabRole1 = new LabRole(1, 1, null, 1, "OWNER");
        String actualLabRole1 = userLabRoles.getHighestLabRole(1);
        assertEquals(expectedLabRole1.role, actualLabRole1);

        /**
         * USER 2 should be an owner
         */
        UserLabRoles userLabRoles2 = new UserLabRoles(2);
        LabRole expectedLabRole2 = new LabRole(2, 2, null, 4, "MEMBER");
        String actualLabRole2 = userLabRoles2.getHighestLabRole(4);
        assertEquals(expectedLabRole2.role, actualLabRole2);
    }

    @Test
    public void hasLabRoleShouldReturnDependingOnUser() {
        /**
         * user 2 should be a member,
         * but not an owner
         * of lab 4
         */
        UserLabRoles userLabRoles = new UserLabRoles(2);
        assertTrue(userLabRoles.hasLabRole(4, "MEMBER"));
        assertFalse(userLabRoles.hasLabRole(4, "OWNER"));
    }
}