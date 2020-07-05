# Stream

## 性能
环境信息：
OS: MacOS Mojave
CPU: 2.2 GHz Intel Core i7
Memory: 16 GB 1600 MHz DDR3
测试场景: 1亿次遍历打印

结论：parallelStream是stream的70倍左右；

## 顺序
### 结论
1. 并行：顺序/乱序计算、顺序/乱序收集
2. 串行：计算、收集是有序的

stream的计算和结果都是有序的；
parallelStream的执行数乱序，结果是有序的；

并行流和串行流只决定任务是否并行，跟收集器的Characteristics是两码事。
collect()方法之后的顺序跟具体收集器有关，如Collectors.toSet()返回的收集器是UNORDERED，而toList()则不是。

影响结果顺序与否的因素：
> https://www.baeldung.com/java-stream-ordering

重点：
1. 中间操作除了sorted(),unsorted(),empty()都不影响结果顺序;
2. 终止操作：
ForEach logs the elements in the order they arrive from each thread.list.stream().parallel().forEach(e -> logger.log(Level.INFO, e));
forEachOrdered保证顺序，即使用于并行流。
list.stream().parallel().forEachOrdered(e -> logger.log(Level.INFO, e));

> https://stackoverflow.com/questions/29710999/is-collect-guaranteed-to-be-ordered-on-parallel-streams

三种顺序：
1. encouter order 
2. processing order 
3. collecting order 
>https://stackoverflow.com/questions/29709140/why-parallel-stream-get-collected-sequentially-in-java-8/29713386#29713386
    
使用unordered()标记流的有序或无序，来决定processing order 
>https://stackoverflow.com/questions/50625544/confusion-about-characteristics-unordered-in-java-8-in-action-book


## parallelStream
并发流用的是forkJoinPool,使用的是全局唯一的commonPool线程池；
parallelStream是创建一个并行的Stream,而且他的并行操作是不具备线程传播性的,所以在使用`ThreadLocal`的时候是无法获取值的.
运行时线程数 = commonPool.size() + main = cpu数量

### 线程安全
paralleStream里直接去修改变量是非线程安全的，但是采用collect和reduce操作就是满足线程安全的了。
### 配置
调节parallelStream的并发线程数可以用参数-Djava.util.concurrent.ForkJoinPool.common.parallelism=N （N为线程数量）

### 使用
1. 确保要执行的任务对线程环境没有依赖，没有共享资源
2. 适用于线程安全的集合，如：CopyOnWriteArrayList，Collections.synchronizedList(new ArrayList<>()) 
3. 适用于可拆分互不影响或有递归操作的`计算密集型`逻辑
4. 任务消耗时间长/数据量大，10000+以上才有必要
5. 结果没有顺序要求
> I/O密集型推荐适用 ComplatableFuture
> https://www.cnblogs.com/xxuan/p/7324009.html