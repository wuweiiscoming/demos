package org.wigo.demo.mydog.shell;

import lombok.SneakyThrows;
import org.jline.reader.*;
import org.jline.reader.impl.completer.ArgumentCompleter;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.TerminalBuilder;

import java.util.List;

/**
 * @author wuwei
 * @since 2021/5/31
 */
public class JLineTest {

    @SneakyThrows
    public static void main(String[] args) {

        Completer completer = new ArgumentCompleter(
                new StringsCompleter("foo11", "foo12", "foo13"),
                new StringsCompleter("foo21", "foo22", "foo23"),
                new Completer() {
                    @Override
                    public void complete(LineReader reader, ParsedLine line, List<Candidate> candidates) {
                        candidates.add(new Candidate("", "", null, "frequency in MHz", null, null, false));
                    }
                });

        LineReader lineReader = LineReaderBuilder.builder()
                .completer(completer)
                .terminal(TerminalBuilder.builder().build())
//                .parser(new MyParser())
                .build();
        String prompt = "mydog-client> ";
        while (true) {
            String line = null;
            try {
                line = lineReader.readLine(prompt);
            } catch (UserInterruptException e) {
                // Ignore
            } catch (EndOfFileException e) {
                return;
            }
        }
    }
}
