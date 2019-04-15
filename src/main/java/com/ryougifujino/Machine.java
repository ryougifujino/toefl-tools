package com.ryougifujino;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by WINFIELD on 2017/4/2.
 */
public class Machine {

    public static String[] PART_OF_SPEACHS = {
            "prep.",
            "pron.",
            "n.",
            "v.",
            "conj.",
            "s.",
            "sc.",
            "o.",
            "oc.",
            "vi.",
            "vt.",
            "aux.v.",
            "a.",
            "ad.",
            "art.",
            "num.",
            "int.",
            "u.",
            "c.",
            "pl."};

    private List<File> readAllFile() {
        List<File> files = new ArrayList<>(48);
        for (int i = 0; i < 48; i++) {
            int index = i + 7;
            files.add(new File("F:\\OTHER\\OLDORIENTED\\text000" +
                    (index < 10 ? "0" + index : index) + ".html"));
        }
        return files;
    }

    private List<WordCell> getStructuredList(Document listDocument) {
        List<WordCell> list = new ArrayList<>(95);
        Elements select = listDocument.select("p[class=subtitle4]");
        select.forEach(element -> {
            WordCell wordCell = new WordCell();
            wordCell.setWord(element.text());
            while (!isNextCell(element)){
                String nextText = element.nextElementSibling().text();
                if (isParaphrase(nextText)){
                    if (wordCell.getParaphrase() != null){
                        wordCell.setParaphrase(wordCell.getParaphrase() + nextText);
                    }else {
                        wordCell.setParaphrase(nextText);
                    }
                }else if (isPurePhonetic(nextText)){
                    wordCell.setPhonetic(nextText);
                }else if (isMnemonic(nextText)){
                    wordCell.setMnemonic(nextText);
                }else if (isExample(nextText)){
                    wordCell.setExample(nextText);
                }
                element = element.nextElementSibling();
            }
            list.add(wordCell);
        });
        return list;
    }

    private boolean isNextCell(Element next) {
        Element element = next.nextElementSibling();
        return element == null || next.nextElementSibling().className().equals("subtitle4");
    }

    private boolean isParaphrase(String text) {
        for (String pfp : PART_OF_SPEACHS) {
            Pattern compile = Pattern.compile("^(\\[.+]\\s)?" + pfp);
            if(compile.matcher(text).find()) return true;
        }
        return false;
    }
    private boolean isPurePhonetic(String text){
        return Pattern.matches("\\[.+]",text);
    }
    private boolean isMnemonic(String text){
        return Pattern.compile("^记　").matcher(text).find();
    }
    private boolean isExample(String text){
        return Pattern.compile("^例　").matcher(text).find();
    }

    private List<List<WordCell>> getStructuredData() throws IOException {
        List<List<WordCell>> vocabulary = new ArrayList<>(48);
        List<File> files = readAllFile();
        for (File file : files) {
            Document listDocument = Jsoup.parse(file, "UTF-8");
            vocabulary.add(getStructuredList(listDocument));
        }
        return vocabulary;
    }


    public static void main(String[] args) throws Exception {
        Machine machine = new Machine();
        List<List<WordCell>> structuredData = machine.getStructuredData();
        List<WordCell> list = new ArrayList<>();
        for (List<WordCell> wordCells : structuredData) {
            for (WordCell wordCell : wordCells) {
                if (wordCell.getParaphrase().contains("促进")){
                    list.add(wordCell);
                }
            }
        }
        System.out.println(list);
    }
}
