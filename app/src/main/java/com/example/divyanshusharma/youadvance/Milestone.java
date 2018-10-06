package com.example.divyanshusharma.youadvance;

import android.graphics.Color;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class Milestone {

    Date start, end;            //date range
    String milestone_text;      //what is the milestone about
    String[] milestone_tags;    // tags associated with the milestone

    public Milestone(Date start, Date end, String milestone_text, String[] milestone_tags) {
        this.start = start;
        this.end = end;
        this.milestone_text = milestone_text;
        this.milestone_tags = milestone_tags;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public String getMilestone_text() {
        return milestone_text;
    }

    public String[] getMilestone_tags() {
        return milestone_tags;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setMilestone_text(String milestone_text) {
        this.milestone_text = milestone_text;
    }

    public void setMilestone_tags(String[] milestone_tags) {
        this.milestone_tags = milestone_tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Milestone milestone = (Milestone) o;
        return Objects.equals(start, milestone.start) &&
                Objects.equals(end, milestone.end) &&
                Objects.equals(milestone_text, milestone.milestone_text);
    }

    
}
