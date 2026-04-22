## 1. 核心考点：Flexible Constructor Bodies（JEP 513）

**一句话总结：打破了 `super()` 必须是构造函数第一行的千年禁令。**

- **面试题：** “Java 构造函数中，`super()` 必须放在第一行吗？”

- **JDK 25 的回答：** 不一定。现在允许在显式调用构造函数（`this()` 或 `super()`）之前编写不引用正在构造的实例的代码。

- **应用场景：** 可以在调用父类构造函数前，先进行**参数校验、逻辑计算或初始化静态字段**。

  Java

    ```
    public class Child extends Parent {
        public Child(int value) {
            // JDK 25 允许在 super 之前执行逻辑
            if (value < 0) throw new IllegalArgumentException("Invalid value"); 
            var transformed = value * 2; 
            super(transformed); 
        }
    }
    ```


---

## 2. 性能考点：Compact Object Headers（JEP 519）

**一句话总结：对象头从 96/128 位压缩到了 64 位，内存占用直接下降。**

- **面试题：** “如何优化大规模 Java 应用的内存占用？”

- **JDK 25 的回答：** 提到 **Project Lilliput**。JDK 25 正式引入了压缩对象头，将 `mark word` 和 `class pointer` 压缩进 64 位。

- **价值：** 这能使典型的 Java 程序内存占用减少 **10%-20%**。在云原生、容器化部署中，这意味着更低的服务器成本。


---

## 3. 并发考点：Scoped Values & Structured Concurrency（正式版/进阶）

**一句话总结：彻底解决虚拟线程环境下 `ThreadLocal` 性能差、易泄露的问题。**

- **面试题：** “在有数百万个虚拟线程的情况下，使用 `ThreadLocal` 会有什么问题？”

- **JDK 25 的回答：** `ThreadLocal` 的内存开销大且不可变性差。

- **新方案：** **Scoped Values**（作用域值）。它是不可变的、生命周期明确的，且在虚拟线程之间共享数据时极其高效。

  Java

    ```
    // ScopedValue 示例
    public final static ScopedValue<User> CURRENT_USER = ScopedValue.newInstance();
    ScopedValue.where(CURRENT_USER, user).run(() -> {
        service.doSomething(); // 在该作用域内自动获取 user
    });
    ```


---

## 4. 语法考点：Primitive Types in Patterns（JEP 507）

**一句话总结：`instanceof` 和 `switch` 终于支持原始类型（int, double等）了。**

- **面试题：** “以前写 `switch` 处理数字和对象很麻烦，JDK 25 有什么优化？”

- **JDK 25 的回答：** 现在模式匹配支持原始类型，不再需要手动封箱/拆箱，也不再需要冗长的类型转换。

  Java

    ```
    if (obj instanceof int i) { ... } // 直接匹配 int 类型
    ```