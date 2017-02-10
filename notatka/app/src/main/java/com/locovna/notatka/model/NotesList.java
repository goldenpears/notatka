package com.locovna.notatka.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A singleton class for create list of [Note] objects
 *
 * @constructor Creates list with 100 fakes notes
 */

public class NotesList {
  private static NotesList sNotesList;
  private List<Note> mNotesList;

  public static NotesList get(Context context){
    /**
     * Get a [NoteList]
     * @return the new list
     */
    if (sNotesList == null){
      sNotesList = new NotesList(context);
    }
    return sNotesList;
  }

  private NotesList(Context context){
    mNotesList = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      Note note = new Note();
      note.setTitle("Lovely note #" + i);
      note.setBody("Body for " + i + " lovely note");
      mNotesList.add(note);
    }
  }

  public List<Note> getNotesList(){
    return mNotesList;
  }

  public Note getNote(UUID id){
    for (Note note : mNotesList){
      if (note.getId().equals(id)){
        return note;
      }
    }
    return null;
  }
}
