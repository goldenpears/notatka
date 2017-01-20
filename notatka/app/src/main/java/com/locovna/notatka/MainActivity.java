package com.locovna.notatka;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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
    notes.add(new Note("My Sweet Salmon", "there is opopop, there is opopop, " +
        "there is opopop, there is opopop"));

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


  }
}
