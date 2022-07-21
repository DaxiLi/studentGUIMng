package com.studentGUI;

import java.util.Calendar;

public class Student {
    private MyDate birth;
    private String name;
    private String studentID;
    private String gender;
    private String school;
    private String grade;
    private String studentClass;
    private String others;//好看就好看
    private Screen screen;

    public Student(){
        this.grade = "";
    }
    public Student(Student student){
        this.birth = new MyDate(student.getBirthDate());
        this.name  = new String(student.getName());
        this.studentID = new String(student.getStudentID());
        this.gender = new String(student.getGender());
        this.school = new String(student.getSchool());
        this.grade  = new String(student.getGrade());
        this.studentClass = new String(student.getStudentClass());
    }

    public Student(String _name,String ID){
        this(_name,ID,new MyDate(2000,1,1),null,null,null,null);
    }

    public Student(String name,String ID,MyDate birth,String gender,String school,String grade,String schoolClass){
        this.birth = birth;
        this.name  = name;
        this.studentID = ID;
        this.gender = gender;
        this.school = school;
        this.grade  = grade;
        this.studentClass = schoolClass;
    }

    public Student(String name, String id, MyDate date) {
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

    public String getBirth() {
        return this.birth == null ? "null":this.birth.toString();
    }
    public MyDate getBirthDate(){return this.birth;}
    public String getOthers(){return this.others;}

    public void setBirth(String s){
        int yIndex = s.indexOf("y=");
        int mIndex = s.indexOf("m=");
        int dIndex = s.indexOf( "d=");
        int rIndex = s.indexOf('}');
        try{
            this.birth = new MyDate(
                    Integer.valueOf(s.substring(yIndex + 2,mIndex - 2)),
                    Integer.valueOf(s.substring(mIndex + 2,dIndex - 2)),
                    Integer.valueOf(s.substring(dIndex + 2,rIndex))
                    );
        }catch (Exception e){
            System.out.println("birth解析失败for :"  + s +"\n" + e.getMessage());
            System.out.println(s.substring(yIndex + 2,mIndex - 2) + s.substring(mIndex + 2,dIndex - 2) + s.substring(dIndex + 2,rIndex));
        }

    }

    public void setOthers(String others){
        this.others = others;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public void setBirth(int y,int m,int d) {
        this.birth = new MyDate(y,m,d);
        System.out.println("birth set as" + y + " " + m + " " + d) ;
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

    public int getAge(){
        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH);
        System.out.println(y);
        System.out.println(m);
        System.out.println("age is" +( (this.birth.m - m < 0 )? (y - this.birth.y - 1) : y - this.birth.y ) );
        return (this.birth.m - m < 0 )? (y - this.birth.y - 1) : y - this.birth.y;
    }

    @Override
    public String toString() {
        return "Student{" +
                "birth=" + birth +
                ", name='" + name + '\'' +
                ", studentID='" + studentID + '\'' +
                ", gender='" + gender + '\'' +
                ", school='" + school + '\'' +
                ", grade='" + grade + '\'' +
                ", studentClass='" + studentClass + '\'' +
                ", others='" + others + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Student student = new Student();
        student.setBirth(2000,1,2);
        String s = student.getBirth();
    }

}

