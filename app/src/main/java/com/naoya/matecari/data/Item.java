package com.naoya.matecari.data;

/**
 * Created by Naoya on 16-01-23.
 */
public final class Item {

    private String id;
    private String name;
    private long num_likes;
    private long num_comments;
    private long price;
    private String photo;

    public String getName() {
        return name;
    }

    public long getNum_likes() {
        return num_likes;
    }

    public long getNum_comments() {
        return num_comments;
    }

    public long getPrice() {
        return price;
    }
}
