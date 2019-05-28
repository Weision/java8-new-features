package com.wxx.mock;

import com.wxx.domain.Student;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MockData {
    private static String str = "ABCDEFGHIGKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static List mockStringList() {
        List list = Collections.EMPTY_LIST;
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        return list;
    }

    public static List mockBigdecialList() {
        List<BigDecimal> list = new ArrayList<>();
        list.add(BigDecimal.valueOf(15l));
        list.add(BigDecimal.ZERO);
        list.add(BigDecimal.valueOf(10l));
        list.add(BigDecimal.ONE);
        return list;
    }

    /*public static List mockStudentList() {
        List<Student> list = Collections.EMPTY_LIST;
        list.add(Student.builder().age(18).name("Weison").score(90).build());
        list.add(Student.builder().age(19).name("Evan").score(80).build());
        list.add(Student.builder().age(20).name("Jack").score(70).build());
        list.add(Student.builder().age(21).name("Luis").score(60).build());
        return list;
    }*/

}
