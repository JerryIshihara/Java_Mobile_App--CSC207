package csc207.fall2018.gamecentreapp;

import org.junit.Test;

import csc207.fall2018.gamecentreapp.GameCentreActivity.User;

import static org.junit.Assert.*;

/**
 * Test for user class
 */
public class UserTest {


    /**
     * Test get password
     */
    @Test
    public void testSetAndGetPassWord() {
        User user1 = new User("Heng", "Lalala");
        assertEquals("Lalala", user1.getPassword());
        user1.setPassword("lalala!");
        assertEquals("lalala!", user1.getPassword());
    }


    /**
    Test get user name
     */
    @Test
    public void testSetAndGetUserName() {
        User user2 = new User("Henry", "I am Handsome");
        assertEquals("Henry", user2.getUserName());
        user2.setUserName("Heng");
        assertEquals("Heng", user2.getUserName());
    }

    /**
    Test toString method
     */
    @Test
    public void testToString() {
        User user3 = new User("Kan", "is handsome");
        assertEquals("Kan", user3.toString());
    }

}