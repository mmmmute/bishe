package com.v1.learn.dto;

public class Grades {
    private int STUDENT_ID;
    private int CLASS_ID;
    private String TEACHER_ID;
    private int GRADE;
    private int ID;
    private String STUDENT_NAME;

    public Grades() {
    }

    public Grades(int STUDENT_ID, int CLASS_ID, String TEACHER_ID, int GRADE, int ID,String name) {
        this.STUDENT_ID = STUDENT_ID;
        this.CLASS_ID = CLASS_ID;
        this.TEACHER_ID = TEACHER_ID;
        this.GRADE = GRADE;
        this.ID = ID;
        this.STUDENT_NAME = name;
    }

    public int getSTUDENT_ID() {
        return STUDENT_ID;
    }

    public void setSTUDENT_ID(int STUDENT_ID) {
        this.STUDENT_ID = STUDENT_ID;
    }

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

    public int getGRADE() {
        return GRADE;
    }

    public void setGRADE(int GRADE) {
        this.GRADE = GRADE;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSTUDENT_NAME() {
        return STUDENT_NAME;
    }

    public void setSTUDENT_NAME(String STUDENT_NAME) {
        this.STUDENT_NAME = STUDENT_NAME;
    }

}
