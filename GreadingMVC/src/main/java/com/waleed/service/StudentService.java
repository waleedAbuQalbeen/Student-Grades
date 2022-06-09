package com.waleed.service;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.waleed.model.CourseInformation;
import com.waleed.model.StudentGrades;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service

public class StudentService {

    private DataSource dataSource;
    private static final String DISPLAY_GRADES_SQL = "select courses.name , grade  from grades,courses where std_id = ? && " +
            "courses.id = grades.curs_id";
    private static final String COURSE_INFORMATION_SQL = "select grade, max(grade) 'Highest mark'," +
            "min(grade) 'lowest mark'," +
            "avg(grade)'average' " +
            "from  grades where curs_id = ? ";

    private static final String LOGIN_SQL = "select name from students where  id = ? && password = ?";
    private static final String ValidStudent_SQL = "select * from grades where  std_id = ? && curs_id = ?";

    public StudentService(){
        try{
            dataSource = getDataSource();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    private  DataSource getDataSource() throws SQLException {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName("localhost");
        ds.setDatabaseName("grading");
        ds.setUser("root");
        ds.setPassword("3011");
        ds.setUseSSL(false);
        ds.setAllowPublicKeyRetrieval(true);

        return ds;
    }
    public ArrayList<StudentGrades> displayGrades(int std_id) throws SQLException {
        ArrayList<StudentGrades> resultSetList = new ArrayList<>();
        try(Connection conn  = dataSource.getConnection()){
            PreparedStatement pStmt = conn.prepareCall(DISPLAY_GRADES_SQL);
            pStmt.setString(1, std_id+"" );
            ResultSet resultSet = pStmt.executeQuery();
            while(resultSet.next()){
                resultSetList.add(new StudentGrades(resultSet.getString("courses.name"),
                        resultSet.getInt("grade")));
            }
        }
        return resultSetList;
    }

    public ArrayList<CourseInformation> courseInformation(int std_id , int course_id) throws SQLException {
        ArrayList<CourseInformation> resultSetList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement pStmt = conn.prepareCall(COURSE_INFORMATION_SQL);
            pStmt.setString(1, +course_id + "");
            ResultSet resultSet = pStmt.executeQuery();

            while (resultSet.next()) {
                CourseInformation course = new CourseInformation();
                course.setGrade(resultSet.getInt("grade"));
                course.setMax(resultSet.getInt("Highest mark"));
                course.setMin(resultSet.getInt("lowest mark"));
                course.setAvg(resultSet.getDouble("average"));

                resultSetList.add(course);
            }
        }
        return resultSetList;
    }

    public boolean isInCourse(int std_id , int curse_id) throws SQLException {
        try(Connection conn  = dataSource.getConnection()){
            PreparedStatement pStmt = conn.prepareCall(ValidStudent_SQL);
            pStmt.setString(1,std_id+"");
            pStmt.setString(2,curse_id+"");
            ResultSet res = pStmt.executeQuery();

            return res.next();

        }
    }
    public boolean logIn(int std_id , String pass)throws SQLException  {
        try(Connection conn  = dataSource.getConnection()){
            PreparedStatement pStmt = conn.prepareCall(LOGIN_SQL);
            pStmt.setString(1,std_id+"");
            pStmt.setString(2,pass+"");
            ResultSet res = pStmt.executeQuery();

            return res.next();

        }
    }



}
