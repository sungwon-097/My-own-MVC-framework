package com.example.test.gradeCalculator;

public class Course {

    private final String subject;
    private final int credit;
    private final String grade;

    public Course(String subject, int credit, String grade) {
        this.subject = subject;
        this.credit = credit;
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public int getCredit() {
        return credit;
    }

    public String getGrade() {
        return grade;
    }

    public double getGradeToNumber() {
        double grade = 0;
        switch (this.grade){
            case "A+":
                grade = 4.5;
                break;
            case "B+":
                grade = 3.5;
                break;
            case "C+":
                grade = 2.5;
                break;
            default: break;
        }
        return grade;
    }

    public double multiplyCreditAndCourseGrade() {
        return credit * getGradeToNumber();
    }// 응집도를 줄임, 이 부분만 수정하면 됨
}
