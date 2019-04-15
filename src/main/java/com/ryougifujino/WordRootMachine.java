package com.ryougifujino;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WINFIELD on 2017/4/8.
 */
public class WordRootMachine {

    private List<File> readAllFile() {
        List<File> files = new ArrayList<>(48);
        for (int i = 0; i < 2; i++) {
            int index = i + 7;
            files.add(new File("F:\\OTHER\\classifyWords\\text0000" + index + ".html"));
        }
        return files;
    }

    static class Counter {
        public int count = 0;
    }

    public static void main(String[] args) throws IOException {
        WordRootMachine machine = new WordRootMachine();
        List<File> files = machine.readAllFile();
        Counter counter = new Counter();
        for (File file : files) {
            Document parse = Jsoup.parse(file, "UTF-8");
            Elements select = parse.select("p[class=normaltext00]");
            select.forEach(element -> {
                counter.count++;
            });
        }
        System.out.println(counter.count);
    }

}
