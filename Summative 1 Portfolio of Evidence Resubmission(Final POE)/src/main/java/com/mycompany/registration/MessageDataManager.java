/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registration;
import java.util.ArrayList;
/**
 *
 * @author Student
 */
public class MessageDataManager {
    
    // Parallel collections to track message variants
    private final ArrayList<Message> sentMessages = new ArrayList<>();
    private final ArrayList<Message> disregardedMessages = new ArrayList<>();
    private final ArrayList<Message> storedMessages = new ArrayList<>();
    
    public MessageDataManager(){}
    
    public void addSentMessage(Message message){
        sentMessages.add(message);
    }
    
    public void addDisregarededMessage(Message message){
        disregardedMessages.add(message);
    }
    
    public void addStoredMessage(Message message){
        storedMessages.add(message);
    }
    
    public ArrayList<Message> getSentMessages(){
        return sentMessages;
    }
    
    public ArrayList<Message> getStoredMessages(){
        return storedMessages;
    }
    
    public ArrayList<Message> getDisregardedMessages() {
        return disregardedMessages;
    }
    
    //Display recipient contacts of all stored data entries
    public String getAllStoredRecipients(){
        if (storedMessages.isEmpty()) 
            return "No stored messages found.";
        
        StringBuilder reportBuilder = new StringBuilder("--- Stored Messages Contact List ---\n");
        
        for (Message message : storedMessages) {
            reportBuilder.append("Recipient Number: ").append(message.getRecipient()).append("\n");
        }
        return reportBuilder.toString(); 
    }
    
    //Locate the longest message text string in memory
    public String findLongestStoredMessage() {
        ArrayList<Message> allActive = new ArrayList<>(sentMessages);
        for (Message m : storedMessages) {
            if (!allActive.contains(m)) allActive.add(m);
        }

        if (allActive.isEmpty()) return "No messages available.";
        
        Message longest = allActive.get(0);
        for (Message message : allActive) {
            if (message.getMessageText().length() > longest.getMessageText().length()) {
                longest = message;
            }
        }
        return longest.getMessageText();
    }
    
    //Search for a message text body using its unique 10-digit ID
    public String searchByMessageId(String id) {
        for (Message message : sentMessages) {
            if (message.getMessageId().equals(id)) return message.getMessageText();
        }
        for (Message message : storedMessages) {
            if (message.getMessageId().equals(id)) return message.getMessageText();
        }
        return "Message ID not found.";
    }
    
    //Pull all messages corresponding to a target cell number
    public String searchAllByRecipient(String recipient) {
        StringBuilder reportBuilder = new StringBuilder();
        ArrayList<Message> allMessages = new ArrayList<>(sentMessages);
        allMessages.addAll(storedMessages);

        for (Message message : allMessages) {
            if (message.getRecipient().equals(recipient)) {
                reportBuilder.append("- ").append(message.getMessageText()).append("\n");
            }
        }
        return reportBuilder.length() == 0 ? "No records found for this recipient." : reportBuilder.toString().trim();
    }
    
    //Delete a specific message entry using its footprint hash key
    public boolean deleteMessageByHash(String hash) {
        for (int i = 0; i < sentMessages.size(); i++) {
            if (sentMessages.get(i).createMessageHash().equalsIgnoreCase(hash)) {
                sentMessages.remove(i);
                return true;
            }
        }
        for (int i = 0; i < storedMessages.size(); i++) {
            if (storedMessages.get(i).createMessageHash().equalsIgnoreCase(hash)) {
                storedMessages.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public String generateSystemReport() {
        if (sentMessages.isEmpty() && storedMessages.isEmpty()) {
            return "System report empty. No active data logged.";
        }
        StringBuilder report = new StringBuilder("\n====================================\n SYSTEM DATA REPORT SUMMARY \n====================================\n");
        ArrayList<Message> combined = new ArrayList<>(sentMessages);
        combined.addAll(storedMessages);

        for (Message message : combined) {
            report.append("Hash Footprint: ").append(message.createMessageHash()).append("\n")
                  .append("Recipient Cell: ").append(message.getRecipient()).append("\n")
                  .append("Message Payload: ").append(message.getMessageText()).append("\n")
                  .append("------------------------------------\n");
        }
        return report.toString();
    }
}