package com.wxx.lambda;


/**
 * lamadba expression
 */
public class Lambda {


    // #2 Method parameter is of type IsTypeOne
    public static void first(IsTypeOne one) {
        //#5 Method that is overridden by Lambda will be called.
        one.hasOne();
    }

    //#6 Method parameter is of type IsTypeTwo
    public static void second(IsTypeTwo two) {
        //#9 Method that is overridden by Lambda will be called.
        two.hasTwo();
    }

    public static void invoke() {
        //#3 Here labmda type is "IsTypeOne", because first() has parameter of type "IsTypeOne"
        //#4 Body {...} of lambda is body part of Overridden hasOne method.
        first(() -> {
            System.out.println("Invoking first.");
        });

        //#7 Here labmda type is "IsTypeTwo", because second() has parameter of type "IsTypeTwo"
        //#8 Body {...} of lambda is body part of Overridden hasTwo method.
        second(() -> {
            System.out.println("Invoking second.");
        });
    }


    public static void main(String[] args) {

        // 1 With Anonymous class.
        IsFunctional isFunc = new IsFunctional() {
            @Override
            public void testMetod(String data) {
                System.out.println("Printing " + data + " from Anonymous class.");
            }
        };

        // 2 With lambda expression.
        IsFunctional func = (demoData) -> {
            System.out.println("Printing " + demoData + " from Lambda expression.");
        };

        // 3 lambda expression as parameter
        invoke();
    }


}
