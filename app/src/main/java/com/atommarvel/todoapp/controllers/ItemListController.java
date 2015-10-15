package com.atommarvel.todoapp.controllers;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.atommarvel.todoapp.models.Item;

import java.util.ArrayList;

/**
 * Created by araiff on 10/14/15.
 */
public class ItemListController {
    private ArrayList<Item> mItemList;
    private ArrayAdapter<Item> mItemArrayAdapter;
    private Persister<Item> mPersister;

    public ItemListController(Context context) {
        mPersister = new Persister<>(context);
        mItemList = mPersister.loadItems();
        if (mItemList == null) {
            mItemList = new ArrayList<>();
        }
        mItemArrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, mItemList);

    }

    public ListAdapter getArrayAdapter() {
        return mItemArrayAdapter;
    }

    public void remove(int pos) {
        mItemList.remove(pos);
        mItemArrayAdapter.notifyDataSetChanged();
        mPersister.saveItems(mItemList);
    }

    public void add(Item item) {
        mItemList.add(item);
        mPersister.saveItems(mItemList);
    }
}
