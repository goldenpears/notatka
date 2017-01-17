package com.locovna.notatka;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ArrayList<Note> notes = new ArrayList<Note>();
    notes.add(new Note("First notatka", "first blood"));
    notes.add(new Note("Second notatka", "there is opopop"));
    notes.add(new Note("My Sweet Salmon", "there is opopop, there is opopop, there is opopop, there is opopop"));

    NoteAdapter mNoteAdapter = new NoteAdapter(this, notes);

    ListView listView = (ListView) findViewById(R.id.listview_note);
    listView.setAdapter(mNoteAdapter);
  }
}
