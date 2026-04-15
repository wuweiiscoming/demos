package org.wigo.demo.mydog.client;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wuwei31
 * @since 2021/6/1
 */
@Data
public class OriginalCommand {

    private String command;

    private List<String> cmdArgs;

    public static final Pattern ARGS_PATTERN = Pattern.compile("\\s*([^\"\']\\S*|\"[^\"]*\"|'[^']*')\\s*");
    public static final Pattern QUOTED_PATTERN = Pattern.compile("^([\'\"])(.*)(\\1)$");

    public OriginalCommand(String line) {
        parseCommand(line);
    }

    private void parseCommand(String line) {
        Matcher matcher = ARGS_PATTERN.matcher(line);

        List<String> args = new LinkedList<>();
        while (matcher.find()) {
            String value = matcher.group(1);
            if (QUOTED_PATTERN.matcher(value).matches()) {
                // Strip off the surrounding quotes
                value = value.substring(1, value.length() - 1);
            }
            args.add(value);
        }
        command = args.get(0);
        cmdArgs = args;
    }

}
