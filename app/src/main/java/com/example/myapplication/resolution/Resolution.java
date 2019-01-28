package com.example.myapplication.resolution;

public class Resolution {
    private String resolutionTitle;
    private String resolutionDescription;
    private String completed;

    public Resolution(String resolutionTitle, String resolutionDescription, String completed) {
        this.resolutionTitle = resolutionTitle;
        this.resolutionDescription = resolutionDescription;
        this.completed = completed;
    }

    public String getResolutionTitle() {
        return resolutionTitle;
    }

    public void setResolutionTitle(String resolutionTitle) {
        this.resolutionTitle = resolutionTitle;
    }

    public String getResolutionDescription() {
        return resolutionDescription;
    }

    public void setResolutionDescription(String resolutionDescription) {
        this.resolutionDescription = resolutionDescription;
    }

    public String isCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }
}
