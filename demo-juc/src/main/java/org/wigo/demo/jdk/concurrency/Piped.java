package org.wigo.demo.jdk.concurrency;

import lombok.SneakyThrows;

import java.io.*;
import java.util.Scanner;

public class Piped {

    public static void main(String[] args) throws IOException {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream();
        in.connect(out);
        new Thread(new Print(in)).start();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            System.out.println("scanner:" + str);
            out.write(str.getBytes());
        }
    }

    static class Print implements Runnable {

        PipedInputStream in;

        public Print(PipedInputStream in) {
            this.in = in;
        }

        @SneakyThrows
        @Override
        public void run() {
            int receive;
            byte[] bytes = new byte[1024];
            while((receive = in.read(bytes)) != -1){
                System.out.println(new String(bytes,0,receive));
            }
            System.out.println("exit");
        }
    }


}
