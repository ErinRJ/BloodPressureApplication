package com.example.bloodpressureapplication.Fragments;

public class Medication {
    String medName;
    String day;
    String time;
    String dosage;
    String notes;
    boolean active;


    public String getDay()
    {
        return day;
    }

    public void setDay(String day)
    {
        this.day = day;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Medication(String medName, String time, String dosage,String notes, String day, boolean active) {
        this.medName = medName;
        this.time = time;
        this.dosage = dosage;
        this.active = active;
        this.notes = notes;
        this.day = day;
    }

}
