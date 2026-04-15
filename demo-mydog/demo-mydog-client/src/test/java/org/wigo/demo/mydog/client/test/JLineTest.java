package org.wigo.demo.mydog.client.test;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;

/**
 * @author wuwei31
 * @since 2021/5/27
 */
public class JLineTest {

    public static void main(String[] args) {
        LineReader lineReader = LineReaderBuilder.builder()
//                .terminal(terminal)
                .build();

        String prompt = "localhost:6379> ";
        while (true) {
            String line;
            line = lineReader.readLine(prompt);
            if (isExit(line)) {
                break;
            }
            System.out.println(line);
        }
    }

    private static boolean isExit(String str) {
        return "exit".equals(str);
    }

}
