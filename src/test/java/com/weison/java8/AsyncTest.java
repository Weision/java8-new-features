package com.weison.java8;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

public class AsyncTest {

    @Test
    public void async() {
        CompletableFuture.supplyAsync(() -> 1)
                .thenApplyAsync(integer -> 2)
                .thenAccept(System.out::println);
        System.out.println();
    }
}
