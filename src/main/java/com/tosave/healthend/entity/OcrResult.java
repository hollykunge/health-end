package com.tosave.healthend.entity;

import java.util.List;

public class OcrResult {
    private List<WordResult> wordsResult;
    private long logId;
    private int wordsResultNum;

    // 构造函数、getters 和 setters
    public OcrResult(List<WordResult> wordsResult, long logId, int wordsResultNum) {
        this.wordsResult = wordsResult;
        this.logId = logId;
        this.wordsResultNum = wordsResultNum;
    }

    public List<WordResult> getWordsResult() {
        return wordsResult;
    }

    public void setWordsResult(List<WordResult> wordsResult) {
        this.wordsResult = wordsResult;
    }

    public long getLogId() {
        return logId;
    }

    public void setLogId(long logId) {
        this.logId = logId;
    }

    public int getWordsResultNum() {
        return wordsResultNum;
    }

    public void setWordsResultNum(int wordsResultNum) {
        this.wordsResultNum = wordsResultNum;
    }
}
