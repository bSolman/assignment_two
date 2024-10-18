package io;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertThrows;

public class ReadWriteTest extends TestCase {

    ReadWrite readWrite = new ReadWrite();

    @Test
    public void testReadFile() throws IOException {
        readWrite.writeToFile("test/testFile.txt", "hej\n");
        assertNotNull(readWrite.readFile("test/testFile.txt").getLast());
    }

    @Test
    public void testWriteToFile() throws IOException {
        assertEquals("hej", readWrite.readFile("test/testFile.txt").getLast());
        assertThrows(FileNotFoundException.class, () -> {
            readWrite.readFile("src/Sprint_Two/gym/logg.tt");
        });
    }

}