package com.wxx.methodReference;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReferenceClient {
    public static void main(String[] args) {

        //method reference three realization

        // 1 Anonymous class.
        IsReferable demo = new IsReferable() {
            @Override
            public void reference() {
                Reference.staticMethod();
            }
        };

        // 2 Lambda implementaion.
        IsReferable demoOne = () -> Reference.staticMethod();

        // 3 Method reference.
        IsReferable demoTwo = Reference::staticMethod;

        //调用执行
        demo.reference();
        demoOne.reference();
        demoTwo.reference();

        //different type method be referenced by lambda

        // 4 引用特定实例方法(非静态方法这样引用)
        IsReferable demoThree = new Reference()::commonMethod;
        demoThree.reference();

        //5 引用任意对象（属于同一个类）的实例方法,主要用于操作集合
        //5.1 例１
        List<Reference> stringArray = new ArrayList();
        stringArray.add(new Reference("Barbara"));
        stringArray.add(new Reference("James"));
        stringArray.add(new Reference("Mary"));
        stringArray.add(new Reference("Weision"));
        stringArray.forEach(Reference::getName);
        //5.2 例2
        String[] strArray = {"c", "d", "e", "John", "f", "g", "a", "b"};
        Arrays.sort(strArray, String::compareToIgnoreCase);
        for (String s : strArray)
            System.out.println("*****" + s + "*****");

        //6 引用构造方法
        IsReferable demoSix = Reference::new;
        demoSix.reference();


    }
}
