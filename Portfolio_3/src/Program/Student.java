package Program;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

            System.out.println(key + " " + value + "\n");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return
                "StudentID=" + studentID +
                ", Name='" + name + '\n' +
                "Postalcode=" + postalcode +
                ", City='" + city + '\'' +
                ", Country='" + country + '\n' + '\n';
    }

    public Integer getStudentID() {
        return studentID;
    }

    public ObservableList<String> getStudentNames(ArrayList<Student> tempStudents){
        ArrayList<String> names = new ArrayList<>();

        for(Student s: tempStudents){
            names.add(s.getName());
        }

        ObservableList<String> output = FXCollections.observableArrayList(names);

        return output;
    }

    public String coursesAndGrades(){
        String output = "";
        for (String name: this.Courses.keySet()) {
            String value = "";
            String key = name.toString();
            if(this.Courses.get(name) == -1){
                value = "null";
            }else {
                value = "" + this.Courses.get(name).toString();
            }
            output += key + " grade: " + value + "\n";
        }
        return output;
    }
}
