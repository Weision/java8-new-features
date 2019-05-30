package com.wxx.domain;

public class MathematicsRepresentative extends Representative {

    public MathematicsRepresentative(String name, Integer age, String sex, Integer score, Integer citationCount) {
        super(name, age, sex, score, citationCount, "数学");
    }

    @Override
    public String toString() {
        return "MathematicsRepresentative{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", score=" + score +
                ", citationCount=" + citationCount +
                '}';
    }
}

