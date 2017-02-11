package com.locovna.notatka;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.locovna.notatka.controller.NoteFragment;

import java.util.UUID;

public class EditorActivity extends SingleFragmentActivity {
  public static final String TAG = EditorActivity.class.getSimpleName();

  public static final String EXTRA_NOTE_ID =
      "com.locovna.notatka.noteintent.noteId";
  public static Intent newIntent(Context packageContext, UUID noteId) {
    Intent intent = new Intent(packageContext, EditorActivity.class);
    intent.putExtra(EXTRA_NOTE_ID, noteId);
    return intent;
  }

  @Override
  protected Fragment createFragment() {
    return new NoteFragment();
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
        //    Intent intent=new Intent(EditorActivity.this,EditorActivity.class);
        //    startActivity(intent);
        return true;
      case R.id.action_delete_all_entries:
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  //    private NoteDbHelper mNoteDbHelper;
  //    mNoteDbHelper = new NoteDbHelper(this);
  //    displayDatabaseInfo();

  //  private void displayDatabaseInfo() {
  //    NoteDbHelper mDbHelper = new NoteDbHelper(this);
  //    SQLiteDatabase db = mDbHelper.getReadableDatabase();
  //
  //    String[] projection = {
  //        NoteContract.NoteEntry._ID,
  //        NoteContract.NoteEntry.COLUMN_NOTE_TITLE,
  //        NoteContract.NoteEntry.COLUMN_NOTE_TEXTBODY,
  //    };
  //
  //    Cursor cursor = db.query(
  //        NoteContract.NoteEntry.TABLE_NAME,         // The table to query
  //        projection,                               // The columns to return
  //        null,                       // The columns for the WHERE clause
  //        null,                       // The values for the WHERE clause
  //        null,                       // don't group the rows
  //        null,                       // don't filter by row groups
  //        null                        // The sort order
  //    );
  //    try {
  //      Log.i(TAG, "Number of rows in notes database table: " + cursor.getCount());
  //
  //      Log.i(TAG, NoteContract.NoteEntry._ID + " - " +
  //          NoteContract.NoteEntry.COLUMN_NOTE_TITLE + "\n");
  //
  //      int idColumnIndex = cursor.getColumnIndexOrThrow(NoteContract.NoteEntry._ID);
  //      int nameColumnIndex = cursor.getColumnIndex(NoteContract.NoteEntry.COLUMN_NOTE_TITLE);
  //      int bodyColumnIndex = cursor.getColumnIndex(NoteContract.NoteEntry.COLUMN_NOTE_TEXTBODY);
  //
  //      ArrayList<Note> notes = new ArrayList<Note>();
  //
  //      final NoteAdapter mNoteAdapter = new NoteAdapter(this, notes);
  //      ListView listView = (ListView) findViewById(R.id.listview_note);
  //      listView.setAdapter(mNoteAdapter);
  //
  //      while (cursor.moveToNext()) {
  //        int currentID = cursor.getInt(idColumnIndex);
  //        String currentTitle = cursor.getString(nameColumnIndex);
  //        String currentBody = cursor.getString(bodyColumnIndex);
  //
  //        Log.i(TAG, currentID + " - " + currentTitle + "\n");
  //        notes.add(new Note(currentTitle, currentBody));
  //      }
  //      cursor.close();
  //    } finally {
  //      cursor.close();
  //    }
  //  }

  //  @Override
  //  protected void onStart() {
  //    super.onStart();
  //    displayDatabaseInfo();
  //  }
}
