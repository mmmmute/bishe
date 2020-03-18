package com.v1.learn.dto;

public class Classes {
    private int CLASS_ID;
    private String TEACHER_ID;
    private int STUDENT_ID;
    private int GRA_CLASS_ID;
    private String CLASS_NAME;
    private String COURSE_NAME;
    private int STUDENT_NUMBER;



    public int getCLASS_ID() {
        return CLASS_ID;
    }

    public void setCLASS_ID(int CLASS_ID) {
        this.CLASS_ID = CLASS_ID;
    }

    public String getTEACHER_ID() {
        return TEACHER_ID;
    }

    public void setTEACHER_ID(String TEACHER_ID) {
        this.TEACHER_ID = TEACHER_ID;
    }

    public int getSTUDENT_ID() {
        return STUDENT_ID;
    }

    public void setSTUDENT_ID(int STUDENT_ID) {
        this.STUDENT_ID = STUDENT_ID;
    }

    public int getGRA_CLASS_ID() {
        return GRA_CLASS_ID;
    }

    public void setGRA_CLASS_ID(int GRA_CLASS_ID) {
        this.GRA_CLASS_ID = GRA_CLASS_ID;
    }

    public String getCLASS_NAME() {
        return CLASS_NAME;
    }

    public void setCLASS_NAME(String CLASS_NAME) {
        this.CLASS_NAME = CLASS_NAME;
    }

    public String getCOURSE_NAME() {
        return COURSE_NAME;
    }

    public void setCOURSE_NAME(String COURSE_NAME) {
        this.COURSE_NAME = COURSE_NAME;
    }

    public int getSTUDENT_NUMBER() {
        return STUDENT_NUMBER;
    }

    public void setSTUDENT_NUMBER(int STUDENT_NUMBER) {
        this.STUDENT_NUMBER = STUDENT_NUMBER;
    }
}
