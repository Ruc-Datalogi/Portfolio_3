package Program;

import java.util.ArrayList;

// Is not used but it could for used for showing the teachers for the different courses
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
