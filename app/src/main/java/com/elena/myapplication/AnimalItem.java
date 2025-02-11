package com.elena.myapplication;

import java.util.List;

public class AnimalItem {
    private String name;
    private List<String> imageUrls;

    public AnimalItem(String name, List<String> imageUrls) {
        this.name = name;
        this.imageUrls = imageUrls;
    }

    public String getName() {
        return name;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }
}

