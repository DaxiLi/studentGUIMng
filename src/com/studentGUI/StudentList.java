package com.studentGUI;

import java.util.ArrayList;
import java.util.List;

public class StudentList {
    List<Student> STUDENTS;
    Screen screen;

    StudentList(){
        STUDENTS = new ArrayList<Student>();
    }

    StudentList(List<Student> list){
        STUDENTS = list;
    }

    public void deleteById(String id){
        List<Student> retList = new ArrayList<Student>();
        for (Student student : STUDENTS) {
            if (!student.getStudentID().equals(id)) {
                retList.add(student);
            }
        }
        STUDENTS = retList;
    }

    public void add(Student student){
        STUDENTS.add(student);
    }
    public void updateStudent(Student student){
        Student temp = getById(student);
        if (temp != null){
            temp = new Student(student);
        }
    }

    public Student getById(Student student){
        for (Student val : STUDENTS) {
            if (val.getStudentID().equals(student.getStudentID())) {
                return student;
            }
        }
        return null;
    }
    public Student getById(String id){
        for (Student val : STUDENTS) {
            if (val.getStudentID().equals(id)) {
                return val;
            }
        }
        return null;
    }

    public List<Student> getSTUDENTS() {
        return STUDENTS;
    }

    public void setSTUDENTS(List<Student> STUDENTS) {
        this.STUDENTS = STUDENTS;
    }

}
