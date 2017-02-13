package com.locovna.notatka.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NotesList {
  private static NotesList sNotesList;
  private List<Note> mNotesList;

  public static NotesList get(Context context){
    if (sNotesList == null){
      sNotesList = new NotesList(context);
    }
    return sNotesList;
  }

  private NotesList(Context context){
    mNotesList = new ArrayList<>();
  }

  public void addNote(Note n){
    mNotesList.add(n);
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
