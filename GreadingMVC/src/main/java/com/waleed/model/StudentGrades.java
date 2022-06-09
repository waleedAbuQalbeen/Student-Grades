package com.waleed.model;

public class StudentGrades  {
 private String courseName;
 private int grade;
 public StudentGrades(){

 }

 @Override
 public String toString() {
  return "StudentGrades{" +
          "courseName='" + courseName + '\'' +
          ", grade=" + grade +
          '}';
 }

 public StudentGrades(String courseName, int grade) {
  this.courseName = courseName;
  this.grade = grade;
 }

 public String getCourseName() {
  return courseName;
 }

 public void setCourseName(String courseName) {
  this.courseName = courseName;
 }

 public int getGrade() {
  return grade;
 }

 public void setGrade(int grade) {
  this.grade = grade;
 }
}
