package com.wxx.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student implements Comparable<Student> {
    String name;
    Integer age;
    String sex;
    Integer score;
    Integer citationCount;


    public int compareTo(Student student) {
        if (this.age >= student.age)
            return 1;
        return -1;
    }

    @Override
    public String toString() {
        return "name: "
                + this.name + " age:" + this.age
                + " score:" + this.score;
    }
}
