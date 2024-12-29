package MedicalManagementSystem123;

import db.DBconnection;

public class Doctor {
    private int doctorId;
    private String doctorName;
    private String specialty;
    private int experience;
    private double fees;

    // Constructor
    public Doctor(int doctorId, String doctorName, String specialty, int experience, double fees) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.specialty = specialty;
        this.experience = experience;
        this.fees = fees;
    }

    // Getters and Setters
    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }
}
