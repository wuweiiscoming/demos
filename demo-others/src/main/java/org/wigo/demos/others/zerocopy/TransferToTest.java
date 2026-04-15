package org.wigo.demos.others.zerocopy;


import org.junit.Test;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author wuwei31
 * @since 2021/6/18
 */
public class TransferToTest {

    @Test
    public void transferTo() throws IOException {
        Path source = Paths.get("C:\\Users\\wuwei31\\Desktop\\source.txt");
        Path target = Paths.get("C:\\Users\\wuwei31\\Desktop\\target.txt");

        try (FileChannel sourceFc = FileChannel.open(source);
             FileChannel targetFc = FileChannel.open(target, StandardOpenOption.APPEND)) {
            long l = sourceFc.transferTo(0, sourceFc.size(), targetFc);
            System.out.println(l);
        }
    }

    @Test
    public void map() throws IOException {
        Path path = Paths.get("C:\\Users\\wuwei31\\Desktop\\source.txt");

        try (FileChannel fc = FileChannel.open(path)) {
            MappedByteBuffer map = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            byte[] dest = new byte[(int) fc.size()];
            map.get(dest);
            System.out.println(new String(dest));
        }
    }

}
