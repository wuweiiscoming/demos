package org.wigo.demos.others.zerocopy;

import org.junit.Test;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MappedByteBufferTest {
    // 1G
    private static long FILE_SIZE = 1 * 1024 * 1024 * 1024L;

    private static String BASE_PATH = "C:\\Users\\wuwei31\\Desktop";

    @Test
    public void stream() {
        try (FileOutputStream fos = new FileOutputStream(BASE_PATH + "\\a.txt");
             DataOutputStream dos = new DataOutputStream(fos)) {

            byte b = (byte) 0;
            for (int i = 0; i < FILE_SIZE; i++) {
                dos.writeByte(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void mappedRw() {
        try (FileChannel channel = FileChannel.open(Paths.get(BASE_PATH, "b.txt"),
                StandardOpenOption.READ, StandardOpenOption.WRITE);) {
            MappedByteBuffer mapBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, FILE_SIZE);
            for (int i = 0; i < FILE_SIZE; i++) {
                mapBuffer.put((byte) 0);
            }
            mapBuffer.flip();
            while (mapBuffer.hasRemaining()) {
                mapBuffer.get();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void mappedPrivate() {
        try (FileChannel channel = FileChannel.open(Paths.get(BASE_PATH, "c.txt"),
                StandardOpenOption.READ, StandardOpenOption.WRITE);) {
            MappedByteBuffer mapBuffer = channel.map(FileChannel.MapMode.PRIVATE, 0, FILE_SIZE);
            for (int i = 0; i < FILE_SIZE; i++) {
                mapBuffer.put((byte) 0);
            }
            mapBuffer.flip();
            while (mapBuffer.hasRemaining()) {
                mapBuffer.get();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}