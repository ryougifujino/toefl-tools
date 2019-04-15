package com.ryougifujino;

/**
 * Created by WINFIELD on 2017/4/2.
 */
public class WordCell {

    private String word;
    private String phonetic;
    private String paraphrase;
    private String mnemonic;
    private String example;

    public String getWord() {
        return word;
    }

    public WordCell setWord(String word) {
        this.word = word;
        return this;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public WordCell setPhonetic(String phonetic) {
        this.phonetic = phonetic;
        return this;
    }

    public String getParaphrase() {
        return paraphrase;
    }

    public WordCell setParaphrase(String paraphrase) {
        this.paraphrase = paraphrase;
        return this;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public WordCell setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
        return this;
    }

    public String getExample() {
        return example;
    }

    public WordCell setExample(String example) {
        this.example = example;
        return this;
    }
}
