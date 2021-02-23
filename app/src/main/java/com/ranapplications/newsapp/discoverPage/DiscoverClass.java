package com.ranapplications.newsapp.discoverPage;

public class DiscoverClass {
    private String title,urlLink;
    private int image;

    public DiscoverClass(String title, String urlLink, int image) {
        this.title = title;
        this.urlLink = urlLink;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
