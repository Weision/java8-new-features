package com.wxx.lambda.enums;

import com.wxx.domain.*;

import java.util.ArrayList;
import java.util.List;

public class LambdaEnum {
    private static List<Student> classOneStudents = new ArrayList<>();
    private static List<Representative> representatives = new ArrayList<>();

    static {

        classOneStudents.add(new Student("Weison", 5, "男", 90, 2));
        classOneStudents.add(new Student("Evan", 5, "女", 60, 1));
        classOneStudents.add(new Student("Jack", 5, "男", 90, 3));
        classOneStudents.add(new Student("Luis", 5, "男", 60, 3));
        classOneStudents.add(new Student("Elen", 5, "女", 90, 5));
        classOneStudents.add(new Student("Obam", 5, "男", 70, 1));
        classOneStudents.add(new Student("Bush", 5, "女", 90, 1));
        representatives.add(new ChineseRepresentative("Evan", 6, "女", 80, 1));
        representatives.add(new MathematicsRepresentative("Jack", 7, "男", 70, 3));
        representatives.add(new EnglishRepresentative("Luis", 8, "女", 60, 3));
    }

    public static void main(String[] args) {
        SchoolClass classOne = SchoolClass.builder().className("classOne").students(classOneStudents).build();
        representatives.stream()
                .forEach(representative ->
                        RepresentativeEnum.getSchoolClass(representative)
                                .apply(classOne, representative));
        System.out.println("----->" + classOne);

    }
}

