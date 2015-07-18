package com.pheng.abel.badthingslldo;

import java.util.Date;
import java.util.UUID;

/**
 * Created by mokan on 2015/7/7.
 */
public class BadThing {
    private UUID id;
    private String title;
    private Date date;
    private boolean corrected;

    public BadThing(){
        id = UUID.randomUUID();
        date = new Date();
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isCorrected() {
        return corrected;
    }

    public void setCorrected(boolean corrected) {
        this.corrected = corrected;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
