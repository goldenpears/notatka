package com.locovna.notatka.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.locovna.notatka.R;

/**
 * Created by Darina Locovna on 2/7/17
 */

public class NoteListFragment extends Fragment {

  private RecyclerView mNoteRecyclerView;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_note_list, container,
        false);
    mNoteRecyclerView = (RecyclerView) view
        .findViewById(R.id.note_recycler_view);
    mNoteRecyclerView.setLayoutManager(new LinearLayoutManager
        (getActivity()));
    return view;
  }
}
