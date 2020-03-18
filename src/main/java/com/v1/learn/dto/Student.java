package com.v1.learn.dto;

public class Student {
    private int STUDENT_ID;
    private String NAME;
    private String PASSWORD;

    public Student(int STUDENT_ID, String NAME, String PASSWORD) {
        this.STUDENT_ID = STUDENT_ID;
        this.NAME = NAME;
        this.PASSWORD = PASSWORD;
    }

    public int getSTUDENT_ID() {
        return STUDENT_ID;
    }

    public void setSTUDENT_ID(int STUDENT_ID) {
        this.STUDENT_ID = STUDENT_ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
}
