package com.weison.java8;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
public class AsyncTest {

    @Test
    public void sync() {
        CompletableFuture.supplyAsync(this::sleep)
                .thenAccept(System.out::println);
        log.info("sync end");
    }

    @SneakyThrows
    public Boolean sleep() {
        TimeUnit.SECONDS.sleep(1L);
        return Boolean.TRUE;
    }
}
