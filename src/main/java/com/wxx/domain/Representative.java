package com.wxx.domain;

public abstract class Representative extends Student {

    private String subject;

    public Representative(String name, Integer age, String sex, Integer score, Integer citationCount, String subject) {
        super(name, age, sex, score, citationCount);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}

