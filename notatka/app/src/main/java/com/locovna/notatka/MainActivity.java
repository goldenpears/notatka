package com.locovna.notatka;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.locovna.notatka.data.NoteContract;
import com.locovna.notatka.data.NoteDbHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  public static final String TAG = MainActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    ArrayList<Note> notes = new ArrayList<Note>();
    notes.add(new Note("First notatka", "first blood"));
    notes.add(new Note("Second notatka", "there is opopop"));
    notes.add(new Note("My Sweet Salmon", "there is opopop, there is opopop, " +
        "there is opopop, there is opopop"));

    final NoteAdapter mNoteAdapter = new NoteAdapter(this, notes);
    ListView listView = (ListView) findViewById(R.id.listview_note);
    listView.setAdapter(mNoteAdapter);

    // Setup each item to open EditorActivity
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Note currentNote = mNoteAdapter.getItem(position);

        Intent intent = new Intent(MainActivity.this, EditorActivity.class);
        startActivity(intent);
      }
    });

    displayDatabaseInfo();

  }

  private void displayDatabaseInfo() {
    // To access our database, we instantiate our subclass of SQLiteOpenHelper
    // and pass the context, which is the current activity.
    NoteDbHelper mDbHelper = new NoteDbHelper(this);

    // Create and/or open a database to read from it
    SQLiteDatabase db = mDbHelper.getReadableDatabase();

    // Perform this raw SQL query "SELECT * FROM notes"
    // to get a Cursor that contains all rows from the notes table.
    Cursor cursor = db.rawQuery("SELECT * FROM " + NoteContract.NoteEntry.TABLE_NAME, null);
    try {
      Log.i(TAG, "Number of rows in notes database table: " + cursor.getCount());
    } finally {
      cursor.close();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_notelist, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_new_note:
        Intent intent = new Intent(MainActivity.this, EditorActivity.class);
        startActivity(intent);
      case R.id.action_insert_dummy_data:
        // Do nothing for now
        return true;
      case R.id.action_delete_all_entries:
        // Do nothing for now
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
