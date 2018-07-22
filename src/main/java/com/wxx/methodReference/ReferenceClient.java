package com.wxx.methodReference;

import com.wxx.defaultMethod.IsReferable;

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

        //

    }
}
