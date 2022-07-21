package com.studentGUI;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Screen {
    private MyDate leftDate;
    private MyDate rightDate;
    private String name;
    private String studentID;
    private String gender;
    private String school;
    private String grade;
    private String studentClass;

    Screen(){
        this.leftDate = new MyDate(MyDate.MIN_YEAR,1,1);
        this.rightDate = new MyDate( Calendar.getInstance().get(Calendar.YEAR),12,31);
        this.name = "";
        this.studentClass = "";
        this.gender = "";
        this.studentID = "";
        this.grade = "";
        this.school = "";
    }
    public List<Student> screenStudents(List<Student> list){
        List<Student> retList = new ArrayList<Student>();
        System.out.println("正在筛选，条件为：\n" + this.toString());
        for (Student student: list){
            if (this.name.length() != 0 && !this.name.equals(student.getName())){ continue;}
            if (this.gender.length() != 0 && !this.gender.equals(student.getGender())){continue;}
            if (this.studentID.length() != 0 && !this.studentID.equals(student.getStudentID())){continue;}
            if (this.school.length() != 0 && !this.school.equals(student.getSchool())){continue;}
            if (this.studentClass.length() != 0 && !this.studentClass.equals(student.getStudentClass())){continue;}
            if (this.leftDate.myEquals(student.getBirthDate()) >= 0){continue;}
            if (this.rightDate.myEquals(student.getBirthDate()) < 0){continue;}
            retList.add(student);
        }
        return retList;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public String getSchool() {
        return school;
    }

    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getGender() {
        return gender;
    }

    public MyDate getLeftDate(){
        return leftDate;
    }
    public MyDate getRightDate(){
        return rightDate;
    }





    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }


    public void setLeftDate(MyDate leftDate) {
        this.leftDate = leftDate;
    }

    public void setRightDate(MyDate rightDate) {
        this.rightDate = rightDate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setSchool(String school){
        this.school = school;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ScreenFrame{" +
                "leftDate=" + leftDate +
                ", rightDate=" + rightDate +
                ", name='" + name + '\'' +
                ", studentID='" + studentID + '\'' +
                ", gender='" + gender + '\'' +
                ", school='" + school + '\'' +
                ", grade='" + grade + '\'' +
                ", studentClass='" + studentClass + '\'' +
                '}';
    }
}
