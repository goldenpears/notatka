package com.locovna.notatka;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.locovna.notatka.data.NoteContract;
import com.locovna.notatka.data.NoteDbHelper;

import butterknife.BindView;

public class EditorActivity extends AppCompatActivity {

  @BindView(R.id.edit_note_title) EditText mTitleEditText;
  @BindView(R.id.edit_note_textbody) EditText mTextbodyEditText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_editor);
  }

  private void insertNote() {
    NoteDbHelper mNoteDbHelper;

    String titleString = mTitleEditText.getText().toString().trim();
    String textbodyString = mTextbodyEditText.getText().toString().trim();

    mNoteDbHelper = new NoteDbHelper(this);
    SQLiteDatabase db = mNoteDbHelper.getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put(NoteContract.NoteEntry.Companion.getCOLUMN_NOTE_TITLE(), titleString);
    values.put(NoteContract.NoteEntry.Companion.getCOLUMN_NOTE_TEXTBODY(), textbodyString);

    long newRowId = db.insert(NoteContract.NoteEntry.Companion.getTABLE_NAME(), null, values);

    if (newRowId == -1) {
      Toast.makeText(this, "Error saving note", Toast.LENGTH_LONG).show();
    } else {
      Toast.makeText(this, "Note saved with id: " + newRowId, Toast.LENGTH_LONG).show();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_editor, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_save:
        insertNote();
        finish();
        return true;
      case R.id.action_delete:
        // Do nothing for now
        return true;
      case android.R.id.home:
        NavUtils.navigateUpFromSameTask(this);
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
