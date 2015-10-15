package com.atommarvel.todoapp.controllers;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.atommarvel.todoapp.models.Item;
import com.atommarvel.todoapp.views.ItemAdapter;

import java.util.ArrayList;

/**
 * Created by araiff on 10/14/15.
 */
public class ItemListController {
    private ArrayList<Item> mItemList;
    private ArrayAdapter<Item> mItemArrayAdapter;
    private Persister mPersister;

    public ItemListController(Context context) {
        mPersister = new Persister(context);
        mItemList = mPersister.loadItems();
        if (mItemList == null) {
            mItemList = new ArrayList<>();
        }
        mItemArrayAdapter = new ItemAdapter(context, mItemList);

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
        mItemArrayAdapter.notifyDataSetChanged();
        mPersister.saveItems(mItemList);
    }

    public Item getItem(int pos) {
        return mItemList.get(pos);
    }

    public void replaceItem(int pos, Item item) {
        mItemList.set(pos, item);
        mItemArrayAdapter.notifyDataSetChanged();
        mPersister.saveItems(mItemList);
    }
}
