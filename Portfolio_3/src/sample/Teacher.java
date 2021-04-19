package sample;

import java.util.ArrayList;

public class Teacher {
    private String name;
    private ArrayList<String> courses;

    Teacher(String name){
        this.name = name;
    }

    public void addCourse(String course){
        this.courses.add(course);
    }

}
