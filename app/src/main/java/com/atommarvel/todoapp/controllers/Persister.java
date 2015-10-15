package com.atommarvel.todoapp.controllers;

import android.content.Context;
import android.util.Log;

import com.atommarvel.todoapp.models.Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by araiff on 10/14/15.
 */
public class Persister {
    private File mTodoFile;

    public Persister(Context context) {
        File filesDir = context.getFilesDir();
        mTodoFile = new File(filesDir, "todo.txt");
    }

    public ArrayList<Item> loadItems() {
        ArrayList<Item> result;
        try {
            Gson gson = new Gson();
            String json  = FileUtils.readFileToString(mTodoFile);
            Type type = new TypeToken<ArrayList<Item>>(){}.getType();
            return (ArrayList<Item>) gson.fromJson(json, type);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveItems(ArrayList<Item> items) {
        String json = new Gson().toJson(items);
        Log.e("atommarvel", json);
        try {
            FileUtils.write(mTodoFile, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
