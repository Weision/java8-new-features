package com.weison.java8;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.nonNull;

/**
 * https://juejin.im/post/6854573213108666381#heading-11
 * 串行 并行 聚合(and&or)
 * {@link #instance instance}
 * {@link #thenSync thenSync}
 * {@link #thenAsync thenAsync}
 * Testing with this music https://www.bilibili.com/video/BV1S4411u777
 */
@Slf4j
public class AsyncTest {

    /**
     * 1. 获取CompletableFuture
     */

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
     * 2. 同步串行关系
     * then 直译【然后】,也就是表示下一步,所以通常是一种串行关系体现,
     * then 后面的单词（比如 run/apply/accept）就是上面说的函数式接口中的抽象方法名称了,
     * 它的作用和那几个函数式接口的作用是一样一样滴
     */
    @Test
    public void thenSync() {

        //串行关系 thenRun thenApply thenAccept thenCompose
        //聚合And关系 thenCombine thenAcceptBoth runAfterBoth
        //聚合Or关系 applyToEither acceptEither runAfterEither
        //异常处理 exceptionally whenComplete handle
        //exceptionally = try/catch
        //whenComplete&handle = try/finally

        //1 thenRun
        CompletableFuture<String> future = CompletableFuture.supplyAsync(this::carAccident)
                .exceptionally(exception -> print(exception.getMessage()));
        String carAccident = future.join();

        CompletableFuture.supplyAsync(() -> print("场景2:坐在公交车上的小明打了119"))
                .thenRun(() -> log.info("场景3:还有人打了120"));

        //2 thenApply thenAccept
        CompletableFuture.supplyAsync(() -> printAndReturn("场景4:2辆消防车来了", "场景5:战士们迅速灭火"))
                .thenApply(str -> printAndReturn(str, "场景6:火很快就被扑灭了"))
                .thenAccept(log::info);

        CompletableFuture<String> future1 =
                CompletableFuture.supplyAsync(() -> printAndReturn("场景7:司机父亲哭的很厉害", "场景8:120把女司机抬上了车"))
                        .thenApply(str -> printAndReturn(str, "北京交通委提醒您:行车不规范,亲人两行泪!"));

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> print("场景9:女司机丈夫受到岳父电话"))
                .thenApply(str -> print("场景10:丈夫菊花一紧,立马往医院赶"));

        Object join = CompletableFuture.anyOf(future1, future2).join();
        print("==" + join.toString() + "==");

        //抢救开始
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(this::rescue)
                .handle((rescueResult, exception) -> {
                    String result = nonNull(exception) ? exception.getMessage() : rescueResult;
                    return print(result);
                });

        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> print("父亲守着女儿"));
        CompletableFuture<String> future5 = CompletableFuture.supplyAsync(() -> print("丈夫补交了各种费用"));

        //接娃放学
        //幼儿园4点放学
        CompletableFuture<String> future6 = CompletableFuture.anyOf(future3, future4, future5)
                .thenApplyAsync(str -> printAndReturn("到了幼儿园放学时间了", "甜甜"))
                .thenApply(name -> callBrother("娃她大舅和二舅", name));
        //小学5点放学
        CompletableFuture<String> future7 = CompletableFuture.anyOf(future6)
                .thenApplyAsync(str -> printAndReturn("到了小学放学时间了", "萌萌"))
                .thenApply(name -> callBrother("娃她二叔和三叔", name));

        //3 thenCompose 展开&平铺 同flatMap
        //  将两个线程串行连接起来,只有第一个线程返回结果时,才会将返回值作为参数传给第二个线程执行
        CompletableFuture<String> daJiuFuture8 = future6.thenCompose(childName -> pickUpChildFromSchool("大舅", childName, 3L))
                .thenApply(this::print);

        CompletableFuture<String> erJiuFuture9 = future6.thenCompose(childName -> pickUpChildFromSchool("二舅", childName, 1L))
                .thenApply(this::print);

        //4 runAfterBoth 非阻塞方法,且只有当两个线程都在该方法执行之前结束,才会执行该方法
        //future7和future8 都结束后才会执行
        daJiuFuture8.runAfterBoth(erJiuFuture9, () -> log.info("甜甜肯定已经被大舅和二叔接到了!!!"));

        //5 runAfterEither 非阻塞方法,只有在该方法执行之前有线程返回该方法才会执行
        //future7或future8有一个结束后就会执行
        daJiuFuture8.runAfterEither(erJiuFuture9, () -> log.info("甜甜应该已经被大舅或二叔接到了!!!"));

        //6 thenCombine 聚合两个独立Future,两个Future线程的执行是并行的,thenCombine在两个线程都返回结果时执行
        // 如果某个线程出现了异常,则thenCombine不会执行
        CompletableFuture<String> future12 = daJiuFuture8.thenCombine(erJiuFuture9,
                (daJiu, erShu) -> daJiu.substring(0, 4) + "和" + erShu)
                .thenApply(str -> print("当时的场景是这样的:" + str));


        CompletableFuture<String> erShuFuture10 = future7.thenCompose(childName -> pickUpChildFromSchool("二叔", childName, 3L))
                .thenApply(this::print);

        CompletableFuture<String> sanShuFuture11 = future7.thenCompose(childName -> pickUpChildFromSchool("三叔", childName, 1L))
                .thenApply(this::print);

        //7 applyToEither 哪个线程先返回就使用谁的返回结果进入该方法
        // 另一个线程如果执行时间过长则不再执行。该方法参数为函数式接口是有返回类型的
        CompletableFuture<String> future13 = erShuFuture10.applyToEither(sanShuFuture11, shu -> print(shu));

        //8 acceptEither 和上面的方法作用相似,但是该方法是消费式,不会线程不会返回执行结果。
        CompletableFuture<Void> future14 = erShuFuture10.acceptEither(sanShuFuture11, shu -> print(shu));

        //9 thenAcceptBoth 当两个CompletionStage都执行完成后,把结果一块交给thenAcceptBoth来进行消耗
        CompletableFuture<String> fatherFuture = CompletableFuture.supplyAsync(() -> "爸爸");
        CompletableFuture<String> motherFuture = CompletableFuture.supplyAsync(() -> "妈妈");
        fatherFuture.thenAcceptBoth(motherFuture, (father, mother) -> log.info(father + "+" + mother + "=家"));

        log.info("then end");
        sleep(8L);
    }

    private String carAccident() {
        Integer resignation = RandomUtil.randomInt(10);
        if (resignation >= 9.9)
            return "场景1:车辆转过了一个急转弯";
        else
            throw new RuntimeException("场景1:车冲出围挡飞了出去,并着火了");
    }

    private String rescue() {
        log.info("医生们紧急处理着");
        sleep(1L);
        //听天由命
        int resignation = RandomUtil.randomInt(10);
        if (resignation >= 0)
            return "抢救成功";
        else
            throw new RuntimeException("抢救失败");
    }

    /**
     * 2. 串行异步
     */
    @Test
    public void thenAsync() {
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

    private String callBrother(String brother, String wa) {
        log.info(brother + "都接到电话,如果不忙去学校接一下" + wa);
        return wa;
    }

    private CompletableFuture<String> pickUpChildFromSchool(String name, String childName, Long time) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "[" + name + "]在学校门口看到了[" + childName + "]");
        sleep(time);
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

    private String print(String str) {
        log.info(str);
        return str;
    }

    private String printAndReturn(String logStr, String returnStr) {
        log.info(logStr);
        return returnStr;
    }
}
