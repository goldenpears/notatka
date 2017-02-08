package com.locovna.notatka.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.locovna.notatka.R;
import com.locovna.notatka.model.Note;
import com.locovna.notatka.model.NotesList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Darina Locovna on 2/7/17
 */

public class NoteListFragment extends Fragment {

  private RecyclerView mNoteRecyclerView;
  private NoteAdapter mAdapter;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_note_list, container,
        false);
    mNoteRecyclerView = (RecyclerView) view
        .findViewById(R.id.note_recycler_view);
    mNoteRecyclerView.setLayoutManager(new LinearLayoutManager
        (getActivity()));

    updateUI();
    return view;
  }

  private void updateUI() {
    NotesList notesList = NotesList.get(getActivity());
    List<Note> notes = notesList.getNotesList();
    mAdapter = new NoteAdapter(notes);
    mNoteRecyclerView.setAdapter(mAdapter);
  }

  static class NoteHolder extends RecyclerView.ViewHolder {
    private Note mNote;

    @BindView(R.id.list_item_note_title) TextView mNoteTitle;
    @BindView(R.id.list_item_note_body) TextView mNoteBody;

    public NoteHolder(View itemView){
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    public void bindNote(Note note){
      mNote = note;

      mNoteTitle.setText(mNote.getTitle());
      mNoteBody.setText(mNote.getBody());
    }
  }

  private class NoteAdapter extends RecyclerView.Adapter<NoteHolder> {
    private List<Note> mNotes;

    public NoteAdapter(List<Note> notes) {
      mNotes = notes;
    }

    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
      View view = layoutInflater
          .inflate(R.layout.list_item_note, parent, false);
      return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteHolder holder, int position) {
      Note note = mNotes.get(position);
      holder.bindNote(note);
    }

    @Override
    public int getItemCount() {
      return mNotes.size();
    }
  }
}
