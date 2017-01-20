package com.locovna.notatka;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Darina Locovna on 1/17/17
 */

public class NoteAdapter extends ArrayAdapter<Note> {

  public static final String LOG_TAG = NoteAdapter.class.getSimpleName();

  public NoteAdapter(Activity context, ArrayList<Note> note){
    super(context, 0, note);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent){
    View listItemView = convertView;
    if (listItemView == null){
      listItemView = LayoutInflater.from(getContext()).inflate(
          R.layout.list_item, parent, false);
    }

    Note currentNote = getItem(position);

    TextView titleTextView = (TextView) listItemView.findViewById(R.id.note_title);
    titleTextView.setText(currentNote.getTitle());

    TextView bodyTextView = (TextView) listItemView.findViewById(R.id.note_body);
    bodyTextView.setText(currentNote.getBody());

    return listItemView;
  }
}

