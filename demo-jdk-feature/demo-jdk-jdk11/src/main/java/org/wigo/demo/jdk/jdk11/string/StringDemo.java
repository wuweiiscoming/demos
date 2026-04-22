package org.wigo.demo.jdk.jdk11.string;

/**
 * JDK 11 String 新方法示例
 *
 * 面试要点：
 * 1. isBlank() - 判断是否空白（空或只含空白字符）
 * 2. lines() - 按行分割返回 Stream
 * 3. strip()/stripLeading()/stripTrailing() - 去除空白（比 trim 更智能）
 * 4. repeat(int) - 重复字符串
 */
public class StringDemo {

    public static void main(String[] args) {
        // isBlank() vs isEmpty()
        System.out.println("=== isBlank() vs isEmpty() ===");
        String empty = "";
        String blank = "   ";
        String text = "hello";

        System.out.println("empty.isEmpty(): " + empty.isEmpty());    // true
        System.out.println("empty.isBlank(): " + empty.isBlank());     // true
        System.out.println("blank.isEmpty(): " + blank.isEmpty());     // false
        System.out.println("blank.isBlank(): " + blank.isBlank());     // true

        // lines() - 多行文本处理
        System.out.println("\n=== lines() ===");
        String multiline = "line1\nline2\nline3";
        multiline.lines().forEach(System.out::println);
        System.out.println("行数: " + multiline.lines().count());

        // strip() vs trim()
        System.out.println("\n=== strip() vs trim() ===");
        String withWhitespace = "  hello  ";
        System.out.println("原始: '" + withWhitespace + "'");
        System.out.println("trim(): '" + withWhitespace.trim() + "'");
        System.out.println("strip(): '" + withWhitespace.strip() + "'");

        // stripLeading / stripTrailing
        System.out.println("stripLeading(): '" + withWhitespace.stripLeading() + "'");
        System.out.println("stripTrailing(): '" + withWhitespace.stripTrailing() + "'");

        // strip 能处理 Unicode 空白字符，trim 只处理 ASCII 空白
        String unicodeWhitespace = " hello ";  // EN SPACE
        System.out.println("\nUnicode空白处理:");
        System.out.println("trim(): '" + unicodeWhitespace.trim() + "'");      // 不去除
        System.out.println("strip(): '" + unicodeWhitespace.strip() + "'");     // 去除

        // repeat()
        System.out.println("\n=== repeat() ===");
        String str = "abc";
        System.out.println("repeat(3): " + str.repeat(3));
        System.out.println("repeat(0): " + str.repeat(0));  // 空字符串

        // 实际应用：生成分隔线
        String separator = "-".repeat(50);
        System.out.println(separator);
    }
}