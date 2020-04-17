package com.v1.learn.dto;

import java.util.Date;

public class EXP_GRADE {

    private String EXPERIMENT_NAME;
    private String START_TIME;
    private String DEADLINE;
    private int ID;
    private int GRADE;

    public int getGRADE() {
        return GRADE;
    }

    public void setGRADE(int GRADE) {
        this.GRADE = GRADE;
    }

    public String getEXPERIMENT_NAME() {
        return EXPERIMENT_NAME;
    }

    public void setEXPERIMENT_NAME(String EXPERIMENT_NAME) {
        this.EXPERIMENT_NAME = EXPERIMENT_NAME;
    }

    public String getSTART_TIME() {
        return START_TIME;
    }

    public void setSTART_TIME(String START_TIME) {
        this.START_TIME = START_TIME;
    }

    public String getDEADLINE() {
        return DEADLINE;
    }

    public void setDEADLINE(String DEADLINE) {
        this.DEADLINE = DEADLINE;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
