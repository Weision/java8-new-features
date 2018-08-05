package com.wxx.functionalInterface;

public class CommonInterfaceImpl implements CommonInterface, CommonInterface1 {
    @Override
    public void doSomthing() {
        System.out.println("CommonInterfaceImpl.doSomthing(): 我们来学习Java8新特性吧！");
    }

    @Override
    public void defaultMehtod() {
        System.out.println("CommonInterfaceImpl.defaultMehtod()-------------start");
        CommonInterface.super.defaultMehtod();
        CommonInterface1.super.defaultMehtod();
        System.out.println("CommonInterfaceImpl.defaultMehtod()-------------end");
    }

    @Override
    public void anotherDefaultMehtod() {
        System.out.println("CommonInterfaceImpl.anotherDefaultMehtod()-------------start");
        CommonInterface.super.anotherDefaultMehtod();
        CommonInterface1.super.anotherDefaultMehtod();
        System.out.println("CommonInterfaceImpl.anotherDefaultMehtod()-------------end");
    }
}
