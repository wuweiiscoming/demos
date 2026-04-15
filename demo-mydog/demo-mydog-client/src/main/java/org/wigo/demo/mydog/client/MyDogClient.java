package org.wigo.demo.mydog.client;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.wigo.demo.mydog.netty.ClientConnection;

import java.io.IOException;
import java.util.*;

/**
 * @author wuwei
 * @since 2021/5/20
 */
public class MyDogClient {

    private ClientConfig config = new ClientConfig();

    // <Command, CommandDesc>
    static final Map<String, String> systemCommandMap = new HashMap<>();
    static final Map<String, CliCommand> serverCommandMap = new HashMap<>();

    private ClientConnection connection;

    private boolean closed;

    public void parseOptions(String[] args) {
        List<String> argList = Arrays.asList(args);
        Iterator<String> it = argList.iterator();

        while (it.hasNext()) {
            String opt = it.next();
            try {
                if (opt.equals("-h")) {
                    config.setHost(it.next());
                }
                if (opt.equals("-p")) {
                    config.setPort(Integer.parseInt(it.next()));
                }
            } catch (NoSuchElementException e) {
                System.err.println("Error: no argument found for option " + opt);
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        MyDogClient client = new MyDogClient();

        client.parseOptions(args);

        client.startup();
    }

    private void startup() throws InterruptedException, IOException {
        connection = new ClientConnection(config.getHost(), config.getPort());
        connection.connect();

        Terminal terminal = TerminalBuilder.builder()
                .system(true)
                .build();

        LineReader lineReader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();

        String prompt = config.getHost() + ":" + config.getHost() + "> ";
        while (closed) {
            String line;
            line = lineReader.readLine(prompt);
            executeLine(line);
        }
    }

    public void executeLine(String line) throws InterruptedException, IOException {
        OriginalCommand c = new OriginalCommand(line);
        String cmd = c.getCommand();
        if (systemCommandMap.containsKey(cmd)) {
            if ("exit".equals(cmd)) {
                connection.close();
                closed = true;
            }
        }
        if (serverCommandMap.containsKey(cmd)) {
            CliCommand command = serverCommandMap.get(cmd);
            command.exec();
        }

        usage();
    }

    static void usage() {
        System.err.println("ZooKeeper -server host:port -client-configuration properties-file cmd args");
        List<String> cmdList = new ArrayList<String>(systemCommandMap.keySet());
        Collections.sort(cmdList);
        for (String cmd : cmdList) {
            System.err.println("\t" + cmd + " " + serverCommandMap.get(cmd));
        }
    }

}
