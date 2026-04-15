package org.wigo.demo.java.agent.attach;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

/**
 * @author wuwei31
 * @since 2021/7/6
 */
public class AgentDemo {

    /**
     * agentFilePath为agent的路径
     */
    private static String agentFilePath = "D:\\MyProjects\\demos\\demo-java-agent\\demo-java-agent-jar\\target\\demo-java-agent-jar-1.0-SNAPSHOT-jar-with-dependencies.jar";

    private static Semaphore smp = new Semaphore(1);

    @SneakyThrows
    public static void main(String[] args) {
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (int i = 0; i < list.size(); i++) {
            VirtualMachineDescriptor vm = list.get(i);
            System.out.println("[" + i + "] " + vm.id() + " " + vm.displayName());
        }

        System.out.println("Please input an integer to select pid.");

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        String pid = list.get(Integer.parseInt(line)).id();

        System.out.println(("Attaching to target JVM with PID: " + pid));
        VirtualMachine jvm = VirtualMachine.attach(pid);
        jvm.loadAgent(agentFilePath);
        jvm.detach();
        System.out.println("Attached to target JVM and loaded Java agent successfully");
//
//            Thread.sleep(2000);

        Socket socket = new Socket("localhost", 8888);

        printResponseAsync(socket);

        do {
            System.out.println("请输入命令");
            line = scanner.nextLine();
            if ("exit".equals(line)) {
                socket.close();
                return;
            } else {
                socket.getOutputStream().write((line + "\n").getBytes());
                socket.getOutputStream().flush();
                smp.acquire();
            }
        } while (scanner.hasNextLine());
    }

    private static void printResponseAsync(Socket socket) {
        new Thread(() -> {
            try {
                InputStream inputStream = socket.getInputStream();
                Scanner scanner = new Scanner(inputStream);
                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                    smp.release();
                    System.out.println("请输入命令");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
