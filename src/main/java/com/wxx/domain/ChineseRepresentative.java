package com.wxx.domain;

public class ChineseRepresentative extends Representative {

    public ChineseRepresentative(String name, Integer age, String sex, Integer score, Integer citationCount) {
        super(name, age, sex, score, citationCount, "语文");
    }

    @Override
    public String toString() {
        return "ChineseRepresentative{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", score=" + score +
                ", citationCount=" + citationCount +
                '}';
    }
}

