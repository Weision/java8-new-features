package com.wxx.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SchoolClass {
    private String className;
    private List<Student> students;
    private ChineseRepresentative chineseRepresentative;
    private EnglishRepresentative englishRepresentative;
    private MathematicsRepresentative mathematicsRepresentative;

    @Override
    public String toString() {
        return "SchoolClass{" +
                "className='" + className + '\'' +
                ", students=" + students +
                ", chineseRepresentative=" + chineseRepresentative +
                ", englishRepresentative=" + englishRepresentative +
                ", mathematicsRepresentative=" + mathematicsRepresentative +
                '}';
    }
}
