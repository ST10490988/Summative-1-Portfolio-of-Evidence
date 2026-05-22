/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.registration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class MessageTest {
    
    public MessageTest() {
    }
    
    @Test
    public void testRecipientFormatSuccess(){
        Message message = new Message(0, "+27718693002", "Test");
        assertEquals("Cell phone number successfully captured.", message.checkRecipientCell());
    }
    
    @Test
    public void testRecipientFormatfailure(){
        Message message = new Message(0, "0718693002", "Test");
        assertTrue(message.checkRecipientCell().contains("incorrectly formatted"));
    }
    
    @Test
    public void testMessageLengthSuccess(){
        Message message = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", message.checkMessageLength());
    }
    
    @Test
    public void testMessageLengthFailure(){
        String overLimit = "a".repeat(255);
        //Overlimit the text is long 
        Message message = new Message(0, "+27718693002", overLimit);
       
        String expectedErrorMessage = "Message exceeds 250 characters by 5, please reduce the size.";
        assertEquals(expectedErrorMessage, message.checkMessageLength());
    }
    
    @Test
    public void testMessageHash(){
        Message message = new Message(0, "+27718693002", "Hi Tonight");
        String hash = message.createMessageHash();
        assertTrue(hash.endsWith(":0:HITONIGHT"));
    } 
}
