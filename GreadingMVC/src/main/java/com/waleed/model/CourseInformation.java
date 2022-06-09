package com.waleed.model;

public class CourseInformation {
    private int grade , max,min;
    private double avg;

     public CourseInformation(){

     }

    @Override
    public String toString() {
        return "CourseInformation{" +
                "grade=" + grade +
                ", max=" + max +
                ", min=" + min +
                ", avg=" + avg +
                '}';
    }

    public CourseInformation(int grade, int max, int min, double avg) {
        this.grade = grade;
        this.max = max;
        this.min = min;
        this.avg= avg;
    }


    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }
}
