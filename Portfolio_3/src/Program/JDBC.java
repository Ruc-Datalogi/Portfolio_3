package Program;

import java.sql.*;
import java.util.ArrayList;

import static java.sql.DriverManager.getConnection;

public class JDBC {
    private Connection connection = null;
    private String url;
    private PreparedStatement preparedStatementStudents = null;
    private ResultSet resultSet = null;

    JDBC(String url) {
        this.url = url;
    }

    public void connect() {
        try {
            connection = getConnection(url);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }


    // Test function for finding a student
    public String findStudent(Integer studentID) {
        String output = "";
        try {
            preparedStatementStudents.setInt(1, studentID);

            resultSet = preparedStatementStudents.executeQuery();

            if (resultSet == null) {
                return "No records fetched";
            }

            output = resultSet.getString(1) + resultSet.getString(2);
        } catch (SQLException e) {
            System.out.println(e);
        }

        return output;
    }


    // Create an arraylist of students objects
    public ArrayList<Student> createStudents() {
        ArrayList<Student> tempStudents = new ArrayList<>();

        // Let's join on students with location using the studentID
        String sql3 = "SELECT * FROM Students LEFT JOIN Location USING (StudentID)";
        PreparedStatement studentsTable = null;

        try {
            studentsTable = connection.prepareStatement(sql3);
            resultSet = studentsTable.executeQuery();

            while (resultSet != null && resultSet.next()) {
                tempStudents.add(new Student(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(4),
                        resultSet.getString(3), resultSet.getString(5)));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return tempStudents;
    }

    // Function for creating the grades for the students
    public ArrayList<Student> setGrades(ArrayList<Student> students){
        String sql = "SELECT studentID,course,grade FROM Grades";
        PreparedStatement gradesTable;

        try{
            gradesTable = connection.prepareStatement(sql);
            resultSet = gradesTable.executeQuery();

            while (resultSet != null && resultSet.next()){

                for (Student s : students){

                    // Check if it's the correct student
                    if(s.getStudentID() == resultSet.getInt(1)){
                        s.addCourseGrade(resultSet.getString(2),resultSet.getInt(3));

                        // Break so we don't loop through unnecessary students
                        break;
                    }
                }

            }

        } catch (SQLException e){
            System.out.println(e);
        }

        return students;
    }


    public Double averageGradeCourse(String course){
        // Let's not include our null values
        String sql = "SELECT AVG(grade) FROM Grades WHERE Course = ? AND Grade != -1";
        PreparedStatement averageCourse;

        Double output = -1.0;
        try{
            averageCourse = connection.prepareStatement(sql);
            averageCourse.setString(1,course);

            resultSet = averageCourse.executeQuery();
            if(resultSet == null){
                return output;
            }
            output = resultSet.getDouble(1);
        } catch (SQLException e){
            System.out.println(e);
        }

        return output;
    }

    public Double averageGradeStudent(Integer studentID){
        // Let's not include our null values
        String sql = "SELECT AVG(grade) FROM Grades where StudentID = ? AND Grade != -1";
        PreparedStatement averageStudent = null;
        Double output = -1.0;

        try {
            averageStudent = connection.prepareStatement(sql);
            averageStudent.setInt(1,studentID);
            resultSet = averageStudent.executeQuery();
            if(resultSet == null){
                return output;
            }

            output = resultSet.getDouble(1);
        } catch (SQLException e){
            System.out.println(e);
        }

        return output;
    }


    //Get the courses for the combobox
    public ArrayList<String> allCourses(){
        ArrayList<String> allCourses = new ArrayList<>();
        String sql = "SELECT * FROM Courses";
        PreparedStatement courses;

        try {
            courses = connection.prepareStatement(sql);
            resultSet = courses.executeQuery();


            if (resultSet == null){
                return null;
            }

            while (resultSet != null && resultSet.next()){
                allCourses.add(resultSet.getString(1));
            }

        } catch (SQLException e){
            System.out.println(e);
        }

        return allCourses;
    }

}
