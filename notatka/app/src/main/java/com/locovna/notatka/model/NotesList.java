package com.locovna.notatka.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.locovna.notatka.data.NoteCursorWrapper;
import com.locovna.notatka.data.NoteDbHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.locovna.notatka.data.NoteContract.NoteEntry.Companion;

public class NotesList {
  private static NotesList sNotesList;
  private Context mContext;
  private SQLiteDatabase mDatabase;

  public static NotesList get(Context context){
    if (sNotesList == null){
      sNotesList = new NotesList(context);
    }
    return sNotesList;
  }

  private NotesList(Context context){
    mContext = context.getApplicationContext();
    mDatabase = new NoteDbHelper(mContext)
                    .getWritableDatabase();
  }

  public void addNote(Note n){
    ContentValues values = getContentValues(n);
    mDatabase.insert(Companion.getTABLE_NAME(), null, values);
  }

  public List<Note> getNotesList(){
    List<Note> notes = new ArrayList<>();

    NoteCursorWrapper cursor = queryNotes(null, null);

    try{
      cursor.moveToFirst();
      while(!cursor.isAfterLast()) {
        notes.add(cursor.getNote());
        cursor.moveToNext();
      }
    } finally {
      cursor.close();
    }

    return notes;
  }

  public Note getNote(UUID id){
    NoteCursorWrapper cursor = queryNotes(
        Companion.getUUID() + " = ?",
        new String[] {id.toString()}
    );
    try {
      if (cursor.getCount() == 0){
        return null;
      }
      cursor.moveToFirst();
      return cursor.getNote();
    } finally {
      cursor.close();
    }
  }

  private static ContentValues getContentValues(Note note){
    ContentValues values = new ContentValues();
    values.put(Companion.getUUID(), note.getId().toString());
    values.put(Companion.getCOLUMN_NOTE_TITLE(), note.getTitle().toString());
    values.put(Companion.getCOLUMN_NOTE_TEXTBODY(), note.getBody().toString());

    return values;
  }

  public void updateNote(Note note){
    String uuidString = note.getId().toString();
    ContentValues values = getContentValues(note);

    mDatabase.update(Companion.getTABLE_NAME(), values,
        Companion.getUUID() + " = ?",
        new String[] { uuidString });
  }

  private NoteCursorWrapper queryNotes(String whereClause, String[] whereArgs){
    Cursor cursor = mDatabase.query(
                Companion.getTABLE_NAME(),         // The table to query
                null,                               // The columns to return
                whereClause,                       // The columns for the WHERE clause
                whereArgs,                       // The values for the WHERE clause
                null,                       // don't group the rows
                null,                       // don't filter by row groups
                null                        // The sort order
            );
    return new NoteCursorWrapper(cursor);
  }
}
