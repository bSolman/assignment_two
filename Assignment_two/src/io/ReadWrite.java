package io;

import java.io.*;
import java.util.List;

public class ReadWrite {

    public List<String> readFile(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.lines().toList();
        }
    }

    public void writeToFile(String path, String content) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.write(content);
            bw.flush();
        }
    }
}
