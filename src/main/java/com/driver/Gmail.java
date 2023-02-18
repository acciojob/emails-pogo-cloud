package com.driver;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

public class Gmail extends Email {

    private int inboxCapacity;
    private ArrayList<Mail> inbox;
    private ArrayList<Mail> trash;

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        this.inbox = new ArrayList<>();
        this.trash = new ArrayList<>();
    }

    public void receiveMail(Date date, String sender, String message){
        Mail mail = new Mail(date, sender, message);
        if (inbox.size() == inboxCapacity) {
            // Remove the oldest mail from inbox and move it to trash
            Mail oldestMail = inbox.get(0);
            inbox.remove(oldestMail);
            trash.add(oldestMail);
        }
        inbox.add(mail);

    }

    public void deleteMail(String message){
        Iterator<Mail> inboxIterator = inbox.iterator();
        while (inboxIterator.hasNext()) {
            Mail mail = inboxIterator.next();
            if (mail.getMessage().equals(message)) {
                inboxIterator.remove();
                trash.add(mail);
                return;
            }
        }

    }

    public String findLatestMessage(){
        if (inbox.isEmpty()) {
            return null;
        }
        // Sort mails in inbox based on date and return the message of the latest mail
        Collections.sort(inbox);
        return inbox.get(inbox.size() - 1).getMessage();

    }

    public String findOldestMessage(){
        if (inbox.isEmpty()) {
            return null;
        }
        // Sort mails in inbox based on date and return the message of the oldest mail
        Collections.sort(inbox);
        return inbox.get(0).getMessage();

    }

    public int findMailsBetweenDates(Date start, Date end){
        int count = 0;
        for (Mail mail : inbox) {
            Date mailDate = mail.getDate();
            if (mailDate.compareTo(start) >= 0 && mailDate.compareTo(end) <= 0) {
                count++;
            }
        }
        return count;

    }

    public int getInboxSize(){
        return inbox.size();

    }

    public int getTrashSize(){
        return trash.size();

    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
    private static class Mail implements Comparable<Mail> {
        private Date date;
        private String sender;
        private String message;

        public Mail(Date date, String sender, String message) {
            this.date = date;
            this.sender = sender;
            this.message = message;
        }

        public Date getDate() {
            return date;
        }

        public String getSender() {
            return sender;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public int compareTo(Mail other) {
            return date.compareTo(other.date);
        }
    }
}
