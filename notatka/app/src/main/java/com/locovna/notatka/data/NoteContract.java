package com.locovna.notatka.data;

import android.provider.BaseColumns;

/**
 * Created by Darina Locovna on 1/22/17
 */

public class NoteContract {

  private NoteContract(){}

  public static class NoteEntry implements BaseColumns {
    public static final String TABLE_NAME = "notes";

    public final static String _ID = BaseColumns._ID;
    public static final String COLUMN_NOTE_TITLE = "title";
    public static final String COLUMN_NOTE_TEXTBODY = "textbody";
  }
}
