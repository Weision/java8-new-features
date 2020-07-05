package com.weison.domain;

public class EnglishRepresentative extends Representative {

    public EnglishRepresentative(String name, Integer age, String sex, Integer score, Integer citationCount) {
        super(name, age, sex, score, citationCount, "英语");
    }

    @Override
    public String toString() {
        return "EnglishRepresentative{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", score=" + score +
                ", citationCount=" + citationCount +
                '}';
    }
}

