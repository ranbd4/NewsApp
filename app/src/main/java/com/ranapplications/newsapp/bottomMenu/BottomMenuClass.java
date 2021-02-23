package com.ranapplications.newsapp.bottomMenu;

public class BottomMenuClass {
    private String title, context;
    private int onClick, image;

    public BottomMenuClass(String title, String context, int onClick, int image) {
        this.title = title;
        this.context = context;
        this.onClick = onClick;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getOnClick() {
        return onClick;
    }

    public void setOnClick(int onClick) {
        this.onClick = onClick;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
