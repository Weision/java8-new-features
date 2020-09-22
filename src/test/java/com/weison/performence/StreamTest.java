package com.weison.performence;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * @author Weison
 * @date 2020/9/21
 */
@Slf4j
@State(Scope.Thread)
//@State(Scope.Benchmark) 那么在不同线程中，i 这两个字段都是不同的实例
public class StreamTest {

    // 指定参数有三个值
    @Param(value =
            {"1", "2", "3"})
    private Integer number;

    @Test
    @Benchmark //声明一个public方法为基准测试方法
    @BenchmarkMode(Mode.Throughput) // 可以测试某个接口的吞吐量、平均执行时间等指标的数据，当前指定mode为Mode.AverageTime
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS) //测量方法五次 并设置时间单位
    @Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)//预热可以避免首次执行时因多了类加载的耗时而导致测试结果不准备的情况。jvm使用JIT即时编译器，一定的预热次数可让JIT对testGson方法的调用链路完成编译，去掉解释执行对测试结果的影响。
    @OutputTimeUnit(TimeUnit.NANOSECONDS) // 指定输出的耗时时长的单位
    @Fork(1)//出多少个子进程来执行同一基准测试方法
    @Threads(2)//使用多少个线程来执行基准测试方法
    public void stream() {
        for (int i = 0; i < 1000000; i++) {
            log.info("-->" + i);
        }
    }
}
