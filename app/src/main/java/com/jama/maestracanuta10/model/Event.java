package com.jama.maestracanuta10.model;

/**
 * Created by ajaramillo on 12/11/2017.
 */

public class Event {


    private String id;
    private String idStudent;
    private String idMisconduct;
    private Integer date;
    private String comments;
    private String idStatus;

    public Event(String id, String idStudent, String idMisconduct, Integer date, String comments, String idStatus) {
        this.id = id;
        this.idStudent = idStudent;
        this.idMisconduct = idMisconduct;
        this.date = date;
        this.comments = comments;
        this.idStatus = idStatus;
    }

    public Event() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public String getIdMisconduct() {
        return idMisconduct;
    }

    public void setIdMisconduct(String idMisconduct) {
        this.idMisconduct = idMisconduct;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(String idStatus) {
        this.idStatus = idStatus;
    }


}
