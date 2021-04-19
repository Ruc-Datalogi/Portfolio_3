package sample;

import java.sql.*;
import java.util.ArrayList;

import static java.sql.DriverManager.getConnection;

public class JDBC {
    private Connection connection = null;
    private String url;
    private PreparedStatement preparedStatementStudents = null;



    private ResultSet resultSet = null;
    private ResultSet resultSetLocation = null;

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
        ArrayList<Student> allStudents = new ArrayList<>();
        String sql = "SELECT studentID, name FROM STUDENTS";
        String sql2 = "SELECT studentID,city,postcode,country FROM Location";
        PreparedStatement studentsTable = null;
        PreparedStatement locationsTable = null;

        try {
            studentsTable = connection.prepareStatement(sql);
            locationsTable = connection.prepareStatement(sql2);
            resultSet = studentsTable.executeQuery();
            resultSetLocation = locationsTable.executeQuery();

            // The resultsets are the samesize so we can loop through one of them
            while (resultSet != null && resultSet.next()) {
                resultSetLocation.next();

                allStudents.add(new Student(resultSet.getInt(1), resultSet.getString(2), resultSetLocation.getInt(3),
                        resultSetLocation.getString(2), resultSetLocation.getString(4)));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return allStudents;
    }

    // Function for creating the grades for the students
    public ArrayList<Student> getGrades(ArrayList<Student> students){
        String sql = "SELECT studentID,course,grade FROM Grades";
        PreparedStatement gradesTable = null;

        try{
            gradesTable = connection.prepareStatement(sql);
            resultSet = gradesTable.executeQuery();

            while (resultSet != null && resultSet.next()){

                for (Student s : students){

                    // Check if it's the correct student
                    if(s.getStudentID() == resultSet.getInt(1)){
                        s.addCourseGrade(resultSet.getString(2),resultSet.getInt(3));
                    }
                }


            }

        } catch (SQLException e){
            System.out.println(e);
        }


        return students;
    }


    public Integer averageGradeCourse(String course){
        // Let's not include our null values
        String sql = "SELECT AVG(grade) FROM Grades WHERE Course = ? AND Grade != -1";
        PreparedStatement averageCourse = null;

        Integer output = -1;
        try{
            averageCourse = connection.prepareStatement(sql);
            averageCourse.setString(1,course);

            resultSet = averageCourse.executeQuery();
            if(resultSet == null){
                return output;
            }
            output = resultSet.getInt(1);
        } catch (SQLException e){
            System.out.println(e);
        }

        return output;
    }

    public Integer averageGradeStudent(Integer studentID){
        String sql = "SELECT AVG(grade) FROM Grades where StudentID = ? AND Grade != -1";
        PreparedStatement averageStudent = null;
        Integer output = -1;

        try {
            averageStudent = connection.prepareStatement(sql);
            averageStudent.setInt(1,studentID);
            resultSet = averageStudent.executeQuery();
            if(resultSet == null){
                return output;
            }

            output = resultSet.getInt(1);
        } catch (SQLException e){
            System.out.println(e);
        }

        return output;
    }

}
