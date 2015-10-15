package com.atommarvel.todoapp.views;

import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.atommarvel.todoapp.R;
import com.atommarvel.todoapp.controllers.ItemListController;
import com.atommarvel.todoapp.models.Item;


public class ToDoActivity extends ActionBarActivity {

    private final int REQUEST_CODE = 20;
    private ItemListController mTodoItems;
    private ListView lvItems;
    private EditText etNewItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        lvItems = (ListView) findViewById(R.id.lvItems);
        etNewItem = (EditText) findViewById(R.id.etNewItem);
        mTodoItems = new ItemListController(getBaseContext());
        lvItems.setAdapter(mTodoItems.getArrayAdapter());
        setupListViewListeners();
    }

    private void setupListViewListeners() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                mTodoItems.remove(pos);
                return true;
            }
        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                Intent i = new Intent(ToDoActivity.this, EditItemActivity.class);
                i.putExtra("Item", mTodoItems.getItem(pos));
                i.putExtra("pos", pos);
                startActivityForResult(i, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            Item item = (Item) data.getExtras().getSerializable("Item");
            int pos = data.getExtras().getInt("pos");
            mTodoItems.replaceItem(pos, item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_to_do, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addTodoItem(View view) {
        String itemText = etNewItem.getText().toString();
        mTodoItems.add(new Item(itemText));
        etNewItem.setText("");
    }
}
