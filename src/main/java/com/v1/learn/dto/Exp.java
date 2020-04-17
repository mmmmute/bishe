package com.v1.learn.dto;

import java.text.DateFormat;
import java.util.Date;

public class Exp {
    private int CLASS_ID;
    private String EXPERIMENT_NAME;
    private Date START_TIME;
    private Date DEADLINE;
    private int SUBMISSION;
    private Date PUBLISH_TIME;
    private String file;
    private String sTime;
    private String deadline;
    private String pTime;
    private String status;
    private int ID;
    private String TEACHER_ID;
    private String t_s;
    private boolean flag_s;
    private boolean STATUS_COR;

    public int getCLASS_ID() {
        return CLASS_ID;
    }

    public void setCLASS_ID(int classId) {
        this.CLASS_ID = classId;
    }

    public String getEXPERIMENT_NAME() {
        return EXPERIMENT_NAME;
    }

    public void setEXPERIMENT_NAME(String EXPERIMENT_NAME) {
        this.EXPERIMENT_NAME = EXPERIMENT_NAME;
    }

    public Date getSTART_TIME() {
        return START_TIME;
    }

    public void setSTART_TIME(Date START_TIME) {
        this.START_TIME = START_TIME;
    }

    public Date getDEADLINE() {
        return DEADLINE;
    }

    public void setDEADLINE(Date DEADLINE) {
        this.DEADLINE = DEADLINE;
    }

    public int getSUBMISSION() {
        return SUBMISSION;
    }

    public void setSUBMISSION(int SUBMISSION) {
        this.SUBMISSION = SUBMISSION;
    }

    public Date getPUBLISH_TIME() {
        return PUBLISH_TIME;
    }

    public void setPUBLISH_TIME(Date PUBLISH_TIME) {
        this.PUBLISH_TIME = PUBLISH_TIME;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
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

    public String getpTime() {
        return pTime;
    }

    public void setpTime(String pTime) {
        this.pTime = pTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTEACHER_ID() {
        return TEACHER_ID;
    }

    public void setTEACHER_ID(String TEACHER_ID) {
        this.TEACHER_ID = TEACHER_ID;
    }



    public String getT_s() {
        return t_s;
    }

    public void setT_s(String t_s) {
        this.t_s = t_s;
    }

    public boolean isFlag_s() {
        return flag_s;
    }

    public void setFlag_s(boolean flag_s) {
        this.flag_s = flag_s;
    }

    public boolean isSTATUS_COR() {
        return STATUS_COR;
    }

    public void setSTATUS_COR(boolean STATUS_COR) {
        this.STATUS_COR = STATUS_COR;
    }
}
