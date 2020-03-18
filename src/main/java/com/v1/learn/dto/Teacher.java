package com.v1.learn.dto;

public class Teacher {
    private String TEACHER_ID;
    private String PASSWORD;
    private String NAME;


    public Teacher(String TEACHER_ID, String PASSWORD, String NAME) {
        this.TEACHER_ID = TEACHER_ID;
        this.PASSWORD = PASSWORD;
        this.NAME = NAME;
    }

    public String getTEACHER_ID() {
        return TEACHER_ID;
    }

    public void setTEACHER_ID(String TEACHER_ID) {
        this.TEACHER_ID = TEACHER_ID;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }
}
