package com.locovna.notatka;

import android.content.ContentValues;
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
  private NoteDbHelper mNoteDbHelper;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ArrayList<Note> notes = new ArrayList<Note>();
    notes.add(new Note("First notatka", "first blood"));

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

    mNoteDbHelper = new NoteDbHelper(this);
    displayDatabaseInfo();

  }

  private void displayDatabaseInfo() {
    NoteDbHelper mDbHelper = new NoteDbHelper(this);
    SQLiteDatabase db = mDbHelper.getReadableDatabase();

    String[] projection = {
        NoteContract.NoteEntry._ID,
        NoteContract.NoteEntry.COLUMN_NOTE_TITLE,
        NoteContract.NoteEntry.COLUMN_NOTE_TEXTBODY,
    };

    Cursor cursor = db.query(
        NoteContract.NoteEntry.TABLE_NAME,         // The table to query
        projection,                               // The columns to return
        null,                       // The columns for the WHERE clause
        null,                       // The values for the WHERE clause
        null,                       // don't group the rows
        null,                       // don't filter by row groups
        null                        // The sort order
    );
    try {
      Log.i(TAG, "Number of rows in notes database table: " + cursor.getCount());

      Log.i(TAG, NoteContract.NoteEntry._ID + " - " +
          NoteContract.NoteEntry.COLUMN_NOTE_TITLE + "\n");

      int idColumnIndex = cursor.getColumnIndexOrThrow(NoteContract.NoteEntry._ID);
      int nameColumnIndex = cursor.getColumnIndex(NoteContract.NoteEntry.COLUMN_NOTE_TITLE);

      while (cursor.moveToNext()) {
        int currentID = cursor.getInt(idColumnIndex);
        String currentTitle = cursor.getString(nameColumnIndex);
        Log.i(TAG, currentID + " - " + currentTitle + "\n");
      }
      cursor.close();
    } finally {
      cursor.close();
    }
  }

  private void insertNote() {
    SQLiteDatabase db = mNoteDbHelper.getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put(NoteContract.NoteEntry.COLUMN_NOTE_TITLE, "Hola, fella");
    values.put(NoteContract.NoteEntry.COLUMN_NOTE_TEXTBODY, "I'm here but not for a long");

    long newRowId = db.insert(NoteContract.NoteEntry.TABLE_NAME, null, values);
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
        insertNote();
        displayDatabaseInfo();
        return true;
      case R.id.action_delete_all_entries:
        // Do nothing for now
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onStart() {
    super.onStart();
    displayDatabaseInfo();
  }
}
