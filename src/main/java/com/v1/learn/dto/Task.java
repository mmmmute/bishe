package com.v1.learn.dto;

import java.util.Date;

public class Task {
    private  String TEACHER_ID;
    private int ID;
    private Date SUB_TIME;
    private String FILE;
    private int STUDENT_ID;
    private boolean sign;

    private String trans;

    public String getTEACHER_ID() {
        return TEACHER_ID;
    }

    public void setTEACHER_ID(String TEACHER_ID) {
        this.TEACHER_ID = TEACHER_ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getSUB_TIME() {
        return SUB_TIME;
    }

    public void setSUB_TIME(Date SUB_TIME) {
        this.SUB_TIME = SUB_TIME;
    }

    public String getFILE() {
        return FILE;
    }

    public void setFILE(String FILE) {
        this.FILE = FILE;
    }

    public int getSTUDENT_ID() {
        return STUDENT_ID;
    }

    public void setSTUDENT_ID(int STUDENT_ID) {
        this.STUDENT_ID = STUDENT_ID;
    }

    public boolean getSign() {
        return sign;
    }

    public void setSign(boolean sign) {
        this.sign = sign;
    }

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }
}
