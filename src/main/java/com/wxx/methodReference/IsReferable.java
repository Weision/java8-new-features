package com.wxx.methodReference;

/**
 * java8 interface add default and static method
 */
public interface IsReferable {

    //common sbstract method
    public void reference();

    //interface default method
    default void defaultMehtod() {
        System.out.println("This is a default method~");
    }

    //interface static method
    static void staticMethod() {
        System.out.println("This is a static  method~");

    }
}
