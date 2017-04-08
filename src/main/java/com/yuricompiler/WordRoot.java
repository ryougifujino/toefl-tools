package com.yuricompiler;

import java.util.List;

/**
 * Created by WINFIELD on 2017/4/8.
 */
public class WordRoot {

    private String root;
    private List<WordCell> relatedWords;

    public String getRoot() {
        return root;
    }

    public WordRoot setRoot(String root) {
        this.root = root;
        return this;
    }

    public List<WordCell> getRelatedWords() {
        return relatedWords;
    }

    public WordRoot setRelatedWords(List<WordCell> relatedWords) {
        this.relatedWords = relatedWords;
        return this;
    }
}
