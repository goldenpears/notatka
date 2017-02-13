package com.locovna.notatka.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.locovna.notatka.R;
import com.locovna.notatka.model.Note;
import com.locovna.notatka.model.NotesList;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Darina Locovna on 2/7/17
 */

public class NoteFragment extends Fragment {
  public static final String ARG_NOTE_ID = "note_id";
  private Note mNote;

  @BindView(R.id.note_edit_title)
  EditText title;
  @BindView(R.id.note_edit_body)
  EditText body;

  public static NoteFragment newInstance(UUID noteId) {
    Bundle args = new Bundle();
    args.putSerializable(ARG_NOTE_ID, noteId);

    NoteFragment fragment = new NoteFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    UUID noteId = (UUID) getArguments().getSerializable(ARG_NOTE_ID);
    mNote = NotesList.get(getActivity()).getNote(noteId);
  }

  @Override
  public void onPause() {
    super.onPause();

    NotesList.get(getActivity()).updateNote(mNote);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_edit_note, container, false);
    ButterKnife.bind(this, v);

    title.setText(mNote.getTitle());
    body.setText(mNote.getBody());

    title.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        mNote.setTitle(charSequence.toString());
      }

      @Override
      public void afterTextChanged(Editable editable) {

      }
    });

    body.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        mNote.setBody(charSequence.toString());
      }

      @Override
      public void afterTextChanged(Editable editable) {

      }
    });

    return v;
  }

}

