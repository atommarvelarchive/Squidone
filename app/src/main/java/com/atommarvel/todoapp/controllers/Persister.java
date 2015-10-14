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
import java.util.List;

/**
 * Created by araiff on 10/14/15.
 */
public class Persister<E> {
    private File mTodoFile;

    public Persister(Context context) {
        File filesDir = context.getFilesDir();
        mTodoFile = new File(filesDir, "todo.txt");
    }

    public List<E> loadItems() {
        try {
            String json  = FileUtils.readFileToString(mTodoFile);
            Type type = new TypeToken<List<E>>(){}.getType();
            return new Gson().fromJson(json, type);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveItems(List<E> items) {
        String json = new Gson().toJson(items);
        Log.e("atommarvel", json);
        try {
            FileUtils.write(mTodoFile, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
