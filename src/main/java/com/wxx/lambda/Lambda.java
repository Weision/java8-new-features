package com.wxx.lambda;


/**
 * lamadba expression
 */
public class Lambda {
    public static void main(String[] args) {

        // 1 With Anonymous class.
        FuncInterface func = new FuncInterface() {
            @Override
            public int calculate(int x, int y) {
                System.out.println("after calculate x and  y ,the value is: " + x + y);
                return x + y;
            }
        };

        // 执行
        func.calculate(8,2);

        // 2 使用lamdba表达式
        // 调用下边定义的invoke方法，把lamdba表达式作为参数传递
        int invokeResult1 = invoke(((x,y)->{return x+y;}),8,2);
        int invokeResult2 = invoke(((x,y)->{return x*y;}),8,2);
        int invokeResult3 = invoke(((x,y)->{return x%y;}),8,2);
        System.out.println("invokeResult1: " + invokeResult1);
        System.out.println("invokeResult2: " + invokeResult2);
        System.out.println("invokeResult3: " + invokeResult3);
    }

    /**
     * invoke方法
     * @param func
     * @param a
     * @param b
     * @return
     */
    public static int invoke(FuncInterface func, int a, int b) {
        return func.calculate(a, b);
    }


}
