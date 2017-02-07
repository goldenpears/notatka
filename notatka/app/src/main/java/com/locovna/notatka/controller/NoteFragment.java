package com.locovna.notatka.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.locovna.notatka.R;
import com.locovna.notatka.model.Note;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Darina Locovna on 2/7/17
 */

public class NoteFragment extends Fragment{
  private Note mNote;

  @BindView(R.id.note_edit_title)
  EditText title;
  @BindView(R.id.note_edit_body)
  EditText body;

  @Override
  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    mNote = new Note();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState){
    View v = inflater.inflate(R.layout.fragment_note, container, false);
    ButterKnife.bind(this, v);

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
