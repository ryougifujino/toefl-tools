package com.yuricompiler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WINFIELD on 2017/4/9.
 */
public class SentenceCuttingMachine {

    public static void main(String[] args) throws Exception{
        File source = new File("F:\\OTHER\\test.txt");
        BufferedReader reader = new BufferedReader(new FileReader(source));
        List<String> sentences = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null){
            char[] chars = line.toCharArray();
            for (char aChar : chars) {
                if (aChar != '.')
                    builder.append(aChar);
                else {
                    builder.append(aChar);
                    sentences.add(builder.toString());
                    builder.setLength(0);
                }
            }
        }
        for (String sentence : sentences) {
            System.out.println(sentence.trim());
        }
    }
}
