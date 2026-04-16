package org.wigo.demo.jdk.concurrency;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest {

    public static void main(String[] args) throws Exception {
        // 取消注释运行不同的示例
//         basicAsyncExample();
//         thenApplyExample();
//         thenAcceptAndThenRunExample();
         thenComposeExample();
//         thenCombineExample();
//         allOfExample();
//         anyOfExample();
//         exceptionHandlingExample();
//         timeoutExample();
//         realWorldExample();
    }

    // ==================== 基础异步操作 ====================

    /**
     * runAsync: 无返回值的异步任务
     * supplyAsync: 有返回值的异步任务
     */
    public static void basicAsyncExample() throws Exception {
        System.out.println("=== 基础异步操作 ===");

        // 无返回值的异步任务
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            sleep(500);
            System.out.println("runAsync: 异步任务执行完成");
        });

        // 有返回值的异步任务
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            sleep(500);
            return "supplyAsync: 返回结果";
        });

        System.out.println(future2.get());
        future1.get();
    }

    // ==================== 链式调用 ====================

    /**
     * thenApply: 对结果进行转换
     */
    public static void thenApplyExample() throws Exception {
        System.out.println("=== thenApply 转换结果 ===");

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(300);
            return "hello";
        }).thenApply(result -> {
            return result + " world";
        }).thenApply(String::toUpperCase);

        System.out.println(future.get()); // HELLO WORLD
    }

    /**
     * thenAccept: 消费结果，无返回值
     * thenRun: 不关心结果，执行下一个任务
     */
    public static void thenAcceptAndThenRunExample() throws Exception {
        System.out.println("=== thenAccept 和 thenRun ===");

        CompletableFuture.supplyAsync(() -> {
            sleep(200);
            return "结果数据";
        }).thenAccept(result -> {
            System.out.println("消费结果: " + result);
        }).thenRun(() -> {
            System.out.println("任务全部完成");
        }).get();
    }

    // ==================== 组合操作 ====================

    /**
     * thenCompose: 两个有依赖关系的异步任务串联
     * 类似于 flatMap
     */
    public static void thenComposeExample() throws Exception {
        System.out.println("=== thenCompose 串联依赖任务 ===");

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(200);
            return "用户ID-123";
        }).thenCompose(userId -> CompletableFuture.supplyAsync(() -> {
            sleep(200);
            return "根据 " + userId + " 查询到的用户信息";
        }));

        System.out.println(future.get());
    }

    /**
     * thenCombine: 两个独立任务并行执行，最后合并结果
     */
    public static void thenCombineExample() throws Exception {
        System.out.println("=== thenCombine 合并独立任务 ===");

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            sleep(300);
            return "价格: 100元";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            sleep(200);
            return "数量: 5件";
        });

        CompletableFuture<String> result = future1.thenCombine(future2, (price, quantity) -> {
            return price + ", " + quantity + ", 总计: 500元";
        });

        System.out.println(result.get());
    }

    // ==================== 多任务处理 ====================

    /**
     * allOf: 等待所有任务完成
     */
    public static void allOfExample() throws Exception {
        System.out.println("=== allOf 等待所有任务 ===");

        long start = System.currentTimeMillis();

        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            sleep(300);
            return "任务1完成";
        });

        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
            sleep(500);
            return "任务2完成";
        });

        CompletableFuture<String> task3 = CompletableFuture.supplyAsync(() -> {
            sleep(400);
            return "任务3完成";
        });

        // 等待所有任务完成
        CompletableFuture.allOf(task1, task2, task3).join();

        System.out.println("所有任务完成，耗时: " + (System.currentTimeMillis() - start) + "ms");
        System.out.println(task1.get());
        System.out.println(task2.get());
        System.out.println(task3.get());
    }

    /**
     * anyOf: 只要有一个任务完成就返回
     */
    public static void anyOfExample() throws Exception {
        System.out.println("=== anyOf 任一完成即返回 ===");

        CompletableFuture<String> server1 = CompletableFuture.supplyAsync(() -> {
            sleep(new Random().nextInt(500) + 100);
            return "服务器1响应";
        });

        CompletableFuture<String> server2 = CompletableFuture.supplyAsync(() -> {
            sleep(new Random().nextInt(500) + 100);
            return "服务器2响应";
        });

        CompletableFuture<String> server3 = CompletableFuture.supplyAsync(() -> {
            sleep(new Random().nextInt(500) + 100);
            return "服务器3响应";
        });

        @SuppressWarnings("unchecked")
        CompletableFuture<Object> firstCompleted = CompletableFuture.anyOf(server1, server2, server3);

        System.out.println("最快响应: " + firstCompleted.get());
    }

    // ==================== 异常处理 ====================

    /**
     * exceptionally: 捕获异常并返回默认值
     * handle: 处理正常和异常两种情况
     * whenComplete: 类似于 finally，不影响最终结果
     */
    public static void exceptionHandlingExample() throws Exception {
        System.out.println("=== 异常处理 ===");

        // exceptionally 示例
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            if (new Random().nextBoolean()) {
                throw new RuntimeException("随机异常");
            }
            return "正常结果";
        }).exceptionally(ex -> "异常恢复: " + ex.getMessage());

        System.out.println("exceptionally 结果: " + future1.get());

        // handle 示例 - 可以处理正常和异常两种情况
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            int result = 10 / 0;
            return "计算结果: " + result;
        }).handle((result, ex) -> {
            if (ex != null) {
                return "handle捕获异常: " + ex.getMessage();
            }
            return "handle正常结果: " + result;
        });

        System.out.println("handle 结果: " + future2.get());

        // whenComplete 示例 - 类似 finally
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            return "任务结果";
        }).whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("whenComplete: 任务成功，结果=" + result);
            } else {
                System.out.println("whenComplete: 任务失败，异常=" + ex.getMessage());
            }
        });

        System.out.println("whenComplete 结果: " + future3.get());
    }

    // ==================== 超时处理 (Java 8 兼容方式) ====================

    /**
     * Java 8 兼容的超时处理方式
     * Java 9+ 可以使用 completeOnTimeout 和 orTimeout
     */
    public static void timeoutExample() throws Exception {
        System.out.println("=== 超时处理 (Java 8 兼容) ===");

        // 方式1: 使用 get(timeout) 超时抛出 TimeoutException
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            sleep(2000);
            return "耗时结果";
        });

        try {
            String result = future1.get(500, TimeUnit.MILLISECONDS);
            System.out.println("结果: " + result);
        } catch (java.util.concurrent.TimeoutException e) {
            System.out.println("get(timeout) 超时: " + e.getMessage());
        }

        // 方式2: 使用 ScheduledExecutorService 实现超时返回默认值
        java.util.concurrent.ScheduledExecutorService scheduler =
            java.util.concurrent.Executors.newScheduledThreadPool(1);

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            sleep(2000);
            return "耗时结果";
        });

        // 超时后手动完成
        scheduler.schedule(() -> future2.complete("超时默认值"), 500, TimeUnit.MILLISECONDS);

        System.out.println("completeOnTimeout 结果: " + future2.get());
        scheduler.shutdown();
    }

    // ==================== 实战示例 ====================

    /**
     * 模拟实际场景：并行获取用户信息、订单信息、商品信息
     */
    public static void realWorldExample() throws Exception {
        System.out.println("=== 实战示例：并行获取多个数据源 ===");

        long start = System.currentTimeMillis();

        // 模拟获取用户信息
        CompletableFuture<String> userFuture = CompletableFuture.supplyAsync(() -> {
            sleep(200);
            return "用户: 张三, VIP会员";
        });

        // 模拟获取订单信息
        CompletableFuture<String> orderFuture = CompletableFuture.supplyAsync(() -> {
            sleep(300);
            return "订单: #ORD-20240001, 金额: 999元";
        });

        // 模拟获取商品库存
        CompletableFuture<Integer> stockFuture = CompletableFuture.supplyAsync(() -> {
            sleep(250);
            return 100;
        });

        // 组合所有结果
        CompletableFuture<String> result = CompletableFuture.allOf(userFuture, orderFuture, stockFuture)
            .thenApply(v -> {
                String user = userFuture.join();
                String order = orderFuture.join();
                Integer stock = stockFuture.join();
                return String.format("汇总信息:\n  %s\n  %s\n  库存: %d件", user, order, stock);
            });

        System.out.println(result.get());
        System.out.println("总耗时: " + (System.currentTimeMillis() - start) + "ms");
    }

    // ==================== 工具方法 ====================

    private static void sleep(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}