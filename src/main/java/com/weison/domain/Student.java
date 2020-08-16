package com.weison.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student implements Comparable<Student> {
    String name;
    Integer age;
    String sex;
    Integer phone;
    Integer score;
    Integer citationCount;


    public int compareTo(Student student) {
        if (this.age >= student.age)
            return 1;
        return -1;
    }

}
