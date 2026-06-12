/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registration;
import java.util.regex.Pattern;
import java.util.Random;
/**
 *
 * @author Student
 */
public class Message {
    
    private int messageNumber;
    private String messageId;
    private String recipient;
    private String messageText;
    
    public Message(int messageNumber, String recipient, String messageText){
        
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageId = generateMessageId();
        this.messageText = messageText;
    }
    
   private String generateMessageId(){
       
       Random rand = new Random(); 
       long id = (long) (rand.nextDouble() * 10000000000L);
       return String.format("%010d", id);
   }
    
   public String createMessageHash(){
       
       String firstTwoId = messageId.substring(0, 2);
       String[] words =messageText.trim().split("\\s+");
       String firstWord = words[0].toUpperCase();
       String lastWord = words[words.length -1].toUpperCase();
       
       return firstTwoId + ":" + messageNumber + ":" + firstWord + lastWord;
   }
   
   public String checkMessageLength(){
       if(this.messageText != null && this.messageText.length() > 250){
           int excess = this.messageText.length() - 250;
           return "Message exceeds 250 characters by " + excess + ", please reduce the size.";
       }
       
       return "Message ready to send.";
   }
   
   public String checkRecipientCell(){
       if (Pattern.matches("^\\+27[0-9]{9}$", recipient)) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number incorrectly formatted or does not contain international code.";
   }
   
   public String storeMessage(){
       return "{\n" +
               " \"messageId\": \"" + messageId + "\",\n" +
               " \"recipient\": \"" + recipient + "\",\n" +
               " \"messageText\": \"" + messageText + "\",\n" +
               " \"messageHash\": \"" + createMessageHash() + "\",\n" +
               "}";
   }
   
   public String getMessageId() {return messageId;}
   public String getRecipient() {return recipient;}
   public String getMessageText() {return messageText;}
}