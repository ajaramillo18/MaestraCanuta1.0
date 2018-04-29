package com.jama.maestracanuta10.model;

/**
 * Created by ajaramillo on 09/11/2017.
 */
//TODO make a different class for the tutor, so a student can have more than one tutor
public class Student {

    private String id;
    private String name;
    private String tutorPhoneNumber;
    private String tutorName;
    private String tutorEmail;



    public Student(String id, String name, String phoneNumberTutor, String tutorName, String tutorEmail) {
        this.id = id;
        this.name = name;
        this.tutorPhoneNumber = phoneNumberTutor;
        this.tutorName = tutorName;
        this.tutorEmail = tutorEmail;
    }

    public Student() {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getTutorPhoneNumber() {
        return tutorPhoneNumber;
    }

    public void setTutorPhoneNumber(String tutorPhoneNumber) {
        this.tutorPhoneNumber = tutorPhoneNumber;
    }

    public String getTutorEmail() {
        return tutorEmail;
    }

    public void setTutorEmail(String tutorEmail) {
        this.tutorEmail = tutorEmail;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
