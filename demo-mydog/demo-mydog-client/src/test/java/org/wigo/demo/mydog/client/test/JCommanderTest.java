package org.wigo.demo.mydog.client.test;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import lombok.Data;

/**
 * @author wuwei31
 * @since 2021/5/20
 */
@Data
public class JCommanderTest {

    @Parameter(names = {"-v", "--verbose"},
            description = "Enable verbose logging")
    private boolean verbose;

    @Parameter(names = {"-f", "--file"},
            description = "Path and name of file to use",
            required = true)
    private String file;

    @Parameter(names = {"-h", "--help"},
            description = "Help/Usage",
            help = true)
    private boolean help;

    // . . .

    final JCommander commander
            = JCommander.newBuilder()
            .programName("JCommander Demonstration")
            .addObject(this)
            .build();

    public void parse(String... args) {
        commander.parse(args);
        if (help) {
            commander.usage();
        } else {
            System.out.println(
                    "The file name provided is '" + file + "' and verbosity is set to " + verbose);
        }
    }


    public static void main(String[] args) {
        JCommanderTest test = new JCommanderTest();
        test.parse(args);
    }


}
