package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId,int inboxCapacity) {
        super(emailId,inboxCapacity);
        // The inboxCapacity is equal to the maximum value an integer can store.
        calendar=new ArrayList<>();

    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);

    }

    public int findMaxMeetings(){
        int maxMeetings=0;
        Collections.sort(calendar,(a,b)->a.getEndTime().compareTo(b.getEndTime()));
        LocalTime currTime = LocalTime.MIN;
        for (Meeting meeting : calendar) {
            if (meeting.getStartTime().compareTo(currTime) >= 0) {
                maxMeetings++;
                currTime = meeting.getEndTime();
            }
        }
        return maxMeetings;

    }
}
