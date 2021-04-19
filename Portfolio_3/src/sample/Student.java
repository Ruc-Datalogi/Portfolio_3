package sample;

import java.util.ArrayList;
import java.util.HashMap;

public class Student {
    private Integer studentID;
    private String name;
    private Integer postalcode;
    private String city;
    private String country;
    private HashMap<String, Integer> Courses = new HashMap<>();

    Student (Integer studentID, String name, Integer postalcode, String city, String country){
        this.studentID = studentID;
        this.name = name;
        this.postalcode = postalcode;
        this.city = city;
        this.country = country;
    }

    public void addCourseGrade(String course, Integer grade){
        this.Courses.put(course, grade);
    }

    public void printCourses(){
        for (String name: this.Courses.keySet()) {
            String value = "";
            String key = name.toString();
            if(this.Courses.get(name) == -1){
                value = "null";
            }else {
                value = "" + this.Courses.get(name).toString();
            }

            System.out.println(key + " " + value);
        }
    }


    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", name='" + name + '\'' +
                ", postalcode=" + postalcode +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public Integer getStudentID() {
        return studentID;
    }
}
