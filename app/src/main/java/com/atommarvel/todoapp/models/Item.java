package com.atommarvel.todoapp.models;

import java.io.Serializable;

/**
 * Created by araiff on 10/14/15.
 */
public class Item implements Serializable{

    private String mTitle;

    public Item(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
