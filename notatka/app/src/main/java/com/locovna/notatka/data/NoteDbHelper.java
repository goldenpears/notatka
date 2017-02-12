package com.locovna.notatka.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Darina Locovna on 1/22/17
 */

public class NoteDbHelper extends SQLiteOpenHelper{
  public static final int DATABASE_VERSION = 1;
  public static final String DATABASE_NAME = "notes.db";

  public NoteDbHelper(Context c){
    super(c,DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db){
    db.execSQL(SQL_CREATE_ENTRIES);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL(SQL_DELETE_ENTRIES);
    onCreate(db);
  }

  private static final String SQL_CREATE_ENTRIES =
      "CREATE TABLE " +
          NoteContract.NoteEntry.Companion.getTABLE_NAME() + " (" +
          NoteContract.NoteEntry.Companion.get_ID() + " INTEGER PRIMARY KEY AUTOINCREMENT," +
          NoteContract.NoteEntry.Companion.getUUID() + " INTEGER," +
          NoteContract.NoteEntry.Companion.getCOLUMN_NOTE_TITLE() + " TEXT," +
          NoteContract.NoteEntry.Companion.getCOLUMN_NOTE_TEXTBODY() + " TEXT)";

  private static final String SQL_DELETE_ENTRIES =
      "DROP TABLE IF EXISTS " + NoteContract.NoteEntry.Companion.getTABLE_NAME();
}
