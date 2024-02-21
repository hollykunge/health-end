package com.tosave.healthend.entity;

import com.tosave.healthend.entity.Location;

public class WordResult {
    private String words;
    private Location location;

    // 构造函数、getters 和 setters
    public WordResult(String words, Location location) {
        this.words = words;
        this.location = location;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
