package com.wxx.functionalInterface;

import java.util.function.Supplier;

public class FuncInterfaceClient {
    public static void main(String[] args) {
        FuncInterface funcInterface = new FuncInterface() {
            @Override
            public String reference() {
                return "funcInterface,你找到了我，哈哈~";
            }
        };
        String reference = funcInterface.reference();
        System.out.println("reference-->" + reference);

        Supplier supplier = () -> {
            return "supplier,你找到了我，哈哈~";
        };
        Object str = supplier.get();
        System.out.println("str-->" + str.toString());

    }


}
