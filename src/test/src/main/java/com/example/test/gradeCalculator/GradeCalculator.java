package com.example.test.gradeCalculator;

import java.util.List;

public class GradeCalculator {
    private final Courses courses;
    public GradeCalculator(List<Course> courses) {
        this.courses = new Courses(courses);
    }

    public double calculateGrade() {
        // 학점 수 합계
        double totalMultipliedCreditAndCourseGrade = courses.multiplyCreditAndCourseGrade();
        // 수강신청 총 학점 수
        int totalCredit = courses.calculateTotalCompletedCredit();

        return totalMultipliedCreditAndCourseGrade/totalCredit;
    }
}