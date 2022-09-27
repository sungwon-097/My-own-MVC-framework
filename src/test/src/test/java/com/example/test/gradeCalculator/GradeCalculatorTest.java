package com.example.test.gradeCalculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 요구사항
 * 평균학점 계산 방법 = (학점 수 * 교과목 평점)의 합계/수강신청 총학점 수
 * 일급 컬렉션 사용
 */

public class GradeCalculatorTest {
    // 학점계산기 도메인 : 이수한 과목, 학점 계산기
    // 과목객체를 가지는 클래스로 표현
    // 이수한 과목을 전달하여 평균학점 계산 요청 --> 학점 계산기

    @DisplayName("평균 학점을 계산한다")
    @Test
    void calculateGradeTest(){
        List<Course> courses = List.of(new Course("OOP", 3, "A+"),
                new Course("DS", 3, "C+"));
        GradeCalculator gradeCalculator = new GradeCalculator(courses);
        double gradeResult = gradeCalculator.calculateGrade();

        assertThat(gradeResult).isEqualTo(3.5);
    }
}