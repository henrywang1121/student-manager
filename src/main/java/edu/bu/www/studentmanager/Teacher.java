package edu.bu.www.studentmanager;


public class Teacher {
    private String teacherName;
    private String position;
    private int iconId;
    private String highSchool;


    public Teacher(String teacherName, String position, int iconId, String highSchool) {
        super();
        this.teacherName = teacherName;
        this.position = position;
        this.iconId = iconId;
        this.highSchool = highSchool;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconIdId(int teacherId) {
        this.iconId = teacherId;
    }

    public String getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(String highSchool) {
        this.highSchool = highSchool;
    }
}
