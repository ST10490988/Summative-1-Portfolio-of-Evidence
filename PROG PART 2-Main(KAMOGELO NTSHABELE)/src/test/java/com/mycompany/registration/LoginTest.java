/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.registration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class LoginTest {
    
    public LoginTest() {
    }
    
    @Test
    //
    public void testCheckUserName() {
        System.out.println("checkUserName");
        Login instance = new Login();
        
        /*Check for the valid username 
        (Has 5 charecters and  underscore_)*/
        instance.setUsername("kyl_1");
        assertTrue(instance.checkUserName(),"Welcome <user first name>, <user last name> it is great to see you again.");
        
        /*Check for the invalid username 
        (Has more than 5 charecters and does not have a underscore_)*/
        instance.setUsername("kyle!!!!!!!");
        assertFalse(instance.checkUserName(), "Username is not correctly formatted, "
                + "please ensure your username contains an underscore "
                + "and is no more than five cahracters in length.");
    }
    
    @Test
    //
    public void testCheckPasswordComplexity() {
        System.out.println("checkPasswordComplexity");
        Login instance = new Login();
        
        /*Check for the valid password
        (the password has capital letters, numbers
        and special characters)*/
        instance.setPassword("Ch&&sec@ke99!");
        assertTrue(instance.checkPasswordComplexity(), "Password successfully captured");
        
        /*Check for the invalid password
        (the password has no capital letters,no numbers
        and no special characters)*/
        instance.setPassword("password");
        assertFalse(instance.checkPasswordComplexity(), "Password is not correctly formetted, "
                + "please ensure that the password contains at least eight characters, "
                + "a capital letter, a number and a special character.");
    }
    
    @Test
    //Phone number validation
    public void testCheckCellPhoneNumber() {
        System.out.println("checkCellPhoneNumber");
        Login instance = new Login();
        
        /*Check if the number is valid and it
        has the regex pattern(SouthAfricas international code +27)*/
        instance.setCellPhoneNumber("+27838968976");
        assertTrue(instance.checkCellPhoneNumber(), "Cell phone number successfully captured");
        
        //invalid cell phone number
        instance.setCellPhoneNumber("0838768976");
        assertFalse(instance.checkCellPhoneNumber(), "Cell phone number incorrectly formatted "
                + "or does not contain international code");
    }
     
     
}

    
