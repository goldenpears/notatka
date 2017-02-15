package com.locovna.notatka.data;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.locovna.notatka.model.Note;

import java.util.UUID;

import static com.locovna.notatka.data.NoteContract.NoteEntry.Companion;

public class NoteCursorWrapper extends CursorWrapper {
  public NoteCursorWrapper(Cursor cursor){
    super(cursor);
  }

  public Note getNote(){
    String uuid = getString(getColumnIndex(Companion.getUUID()));
    String title = getString(getColumnIndex(Companion.getCOLUMN_NOTE_TITLE()));
    String body = getString(getColumnIndex(Companion.getCOLUMN_NOTE_TEXTBODY()));

    Note note = new Note(UUID.fromString(uuid));
    note.setTitle(title);
    note.setBody(body);
    return note;
  }
}
