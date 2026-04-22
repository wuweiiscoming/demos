package org.wigo.demo.jdk.jdk17.textblock;

/**
 * JDK 17 文本块（Text Blocks）示例
 *
 * 文本块使用三个双引号包裹，可以保留格式，无需转义。
 *
 * 面试要点：
 * 1. 简化多行字符串编写
 * 2. 自动处理缩进（以最左边的非空格字符为基准）
 * 3. 无需转义双引号
 * 4. 适合 SQL、JSON、HTML 等多行文本
 */
public class TextBlockDemo {

    public static void main(String[] args) {
        // 传统方式 vs 文本块
        String oldJson = "{\n" +
                "  \"name\": \"Alice\",\n" +
                "  \"age\": 25\n" +
                "}";

        String newJson = """
                {
                    "name": "Alice",
                    "age": 25
                }
                """;

        System.out.println("=== 传统方式 ===");
        System.out.println(oldJson);

        System.out.println("\n=== 文本块 ===");
        System.out.println(newJson);

        // SQL 示例
        String sql = """
                SELECT id, name, email
                FROM users
                WHERE status = 'active'
                  AND created_at > '2024-01-01'
                ORDER BY created_at DESC
                LIMIT 10
                """;
        System.out.println("\n=== SQL 示例 ===");
        System.out.println(sql);

        // HTML 示例
        String html = """
                <div class="container">
                    <h1>Hello, World!</h1>
                    <p>This is a text block example.</p>
                </div>
                """;
        System.out.println("\n=== HTML 示例 ===");
        System.out.println(html);

        // 嵌入表达式（JDK 15+）
        String name = "Bob";
        int age = 30;
        String message = """
                User Info:
                    Name: %s
                    Age: %d
                """.formatted(name, age);
        System.out.println("\n=== 格式化示例 ===");
        System.out.println(message);

        // 避免末尾换行：使用 \ 在行尾
        String noTrailingNewline = """
                This line has no trailing newline\
                """;
        System.out.println("\n=== 避免末尾换行 ===");
        System.out.println("[" + noTrailingNewline + "]");

        // 单行空格：使用 \s
        String withSpaces = """
                Name:\s\s\s\s\sAlice
                Age:\s\s\s\s\s 25
                """;
        System.out.println("\n=== 使用 \\s 保留空格 ===");
        System.out.println(withSpaces);
    }
}