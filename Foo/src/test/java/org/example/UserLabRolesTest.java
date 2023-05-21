package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserLabRolesTest {

    @Test
    public void getUserShouldReturnUserObject() {
        UserLabRoles userLabRoles = new UserLabRoles(1);
        User expectedUser = new User(1, "joe");
        User actualUser = userLabRoles.getUser();
        assertEquals(expectedUser.id, actualUser.id);
    }
}