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
    
    @Test
    public void testSentMessagesArrayPopulation() {
        MessageDataManager manager = new MessageDataManager();
        Message msg1 = new Message(1, "+27834557896", "Did you get the cake?");
        Message msg2 = new Message(2, "0838884567", "It is dinner time !");
        
        manager.addSentMessage(msg1);
        manager.addSentMessage(msg2);
        
        assertEquals(2, manager.getSentMessages().size());
        assertEquals("Did you get the cake?", manager.getSentMessages().get(0).getMessageText());
    }

    @Test
    public void testDisplayLongestMessage() {
        MessageDataManager manager = new MessageDataManager();
        Message m1 = new Message(1, "+27834557896", "Short message");
        Message m2 = new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        
        manager.addSentMessage(m1);
        manager.addStoredMessage(m2);
        
        assertEquals("Where are you? You are late! I have asked you to be on time.", manager.findLongestStoredMessage());
    }

    @Test
    public void testSearchByMessageId() {
        MessageDataManager manager = new MessageDataManager();
        Message m4 = new Message(4, "+27838884567", "It is dinner time !");
        manager.addSentMessage(m4);
        
        String generatedId = m4.getMessageId();
        assertEquals("It is dinner time !", manager.searchByMessageId(generatedId));
    }

    @Test
    public void testSearchAllByRecipient() {
        MessageDataManager manager = new MessageDataManager();
        Message m1 = new Message(1, "+27834557896", "Hello testing");
        Message m2 = new Message(2, "+27834557896", "Second message context verification query");
        
        manager.addSentMessage(m1);
        manager.addStoredMessage(m2);
        
        String query = manager.searchAllByRecipient("+27834557896");
        assertTrue(query.contains("Hello testing"));
        assertTrue(query.contains("Second message context verification query"));
    }

    @Test
    public void testDeleteMessageByHash() {
        MessageDataManager manager = new MessageDataManager();
        Message m1 = new Message(1, "+27834557896", "Wipe me out from data tracking rows");
        manager.addStoredMessage(m1);
        
        String hashKey = m1.createMessageHash();
        assertTrue(manager.deleteMessageByHash(hashKey));
        assertEquals(0, manager.getStoredMessages().size());
    }
}