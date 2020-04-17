package com.v1.learn.dto;

import java.util.Date;

public class ExpHelp1 {
    private String EXPERIMENT_NAME;
    private String sTime;
    private String deadline;
    private int ID;

    private String STATUS_COR;
    private int price;

    public String getEXPERIMENT_NAME() {
        return EXPERIMENT_NAME;
    }

    public void setEXPERIMENT_NAME(String EXPERIMENT_NAME) {
        this.EXPERIMENT_NAME = EXPERIMENT_NAME;
    }

    public String getsTime() {
        return sTime;
    }

    public void setsTime(String sTime) {
        this.sTime = sTime;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getSTATUS_COR() {
        return STATUS_COR;
    }

    public void setSTATUS_COR(String STATUS_COR) {
        this.STATUS_COR = STATUS_COR;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
