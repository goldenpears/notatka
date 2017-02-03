package com.locovna.notatka.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.locovna.notatka.R;
import com.locovna.notatka.model.Note;

import java.util.ArrayList;

/**
 * Created by Darina Locovna on 2/2/17
 */

public class NoteListFragment extends Fragment{

  private static final String TAG = NoteListFragment.class.getSimpleName();
  private ArrayList<Note> notes;

  @Override
  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
     notes = new ArrayList<Note>();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState){
    View v = inflater.inflate(R.layout.fragment_notelist, container, false);
    v.setTag(TAG);
    return v;
  }
}


