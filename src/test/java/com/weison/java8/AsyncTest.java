package com.weison.java8;

import cn.hutool.http.HttpUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * https://juejin.im/post/6854573213108666381#heading-11
 */
@Slf4j
public class AsyncTest {

    @Test
    @SneakyThrows
    public void instance() {
        CompletableFuture<Boolean> futureOne = CompletableFuture.supplyAsync(this::sleep);
        CompletableFuture<Void> futureOneTwo = CompletableFuture.runAsync(this::sleep);
        Boolean joinOne = futureOne.join();
        Void aVoid = futureOneTwo.get(3, TimeUnit.SECONDS);
        log.info("sync end {},{}", joinOne, aVoid);
        sleep(2L);
    }

    /**
     * then 直译【然后】，也就是表示下一步，所以通常是一种串行关系体现,
     * then 后面的单词（比如 run /apply/accept）就是上面说的函数式接口中的抽象方法名称了，
     * 它的作用和那几个函数式接口的作用是一样一样滴
     */
    @Test
    public void then() {
        CompletableFuture<String> userFuture = CompletableFuture.supplyAsync(this::requestUser)
                .thenApply(body -> doSomeThing(body));

        CompletableFuture<String> orderFuture = CompletableFuture.supplyAsync(this::requestOrder)
                .thenApply(body -> doSomeThing(body));

        CompletableFuture<String> openIdFuture = CompletableFuture.supplyAsync(this::requestWeChat)
                .thenApply(body -> doSomeThing(body));


        //聚合两个独立Future
        CompletableFuture<String> userOrder = userFuture.thenCombine(orderFuture,
                (user, order) -> user + order);

        //展开&平铺 同flatMap
        CompletableFuture<String> weChatInfoFuture = userFuture.thenCompose(userOpenId -> requestWeChat(userOpenId));

        CompletableFuture<CompletableFuture<String>> weChatInfoFutureFuture =
                userFuture.thenApply(userOpenId -> requestWeChat(userOpenId));

        CompletableFuture.anyOf(userFuture,orderFuture,openIdFuture,userOrder,weChatInfoFuture)
                .thenAccept(object->log.info("over",object));

        CompletableFuture.allOf(userFuture,orderFuture,openIdFuture,userOrder,weChatInfoFuture)
                .thenAccept(object->log.info("over",object));
        log.info("then end");
        sleep(5L);
    }

    private String requestUser() {
        String body = HttpUtil.createGet("https://www.baidu.com/")
                .contentType("text/html;charset=utf-8")
                .execute()
                .body();
        return body;
    }

    private String requestOrder() {
        return requestUser();
    }

    private String requestWeChat() {
        return requestUser();
    }

    private CompletableFuture<String> requestWeChat(String openId) {
        CompletableFuture<String> future =
                CompletableFuture.supplyAsync(() -> HttpUtil.createGet("https://www.baidu.com/")
                        .contentType("text/html;charset=utf-8")
                        .execute()
                        .header("openId", openId)
                        .body());
        return future;
    }

    @Test
    public void async1() {
        CompletableFuture.supplyAsync(this::sleep)
                .thenAccept(System.out::println)
                .thenAccept(aVoid -> log.info("over~"));
        log.info("sync end");
        sleep();
    }


    @SneakyThrows
    public Boolean sleep() {
        TimeUnit.SECONDS.sleep(1L);
        return Boolean.TRUE;
    }

    @SneakyThrows
    public Boolean sleep(Long sleep) {
        TimeUnit.SECONDS.sleep(sleep);
        return Boolean.TRUE;
    }


    private String doSomeThing(String body) {
        return body;
    }
}
