package com.locovna.notatka.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.locovna.notatka.EditorActivity;
import com.locovna.notatka.R;
import com.locovna.notatka.model.Note;
import com.locovna.notatka.model.NotesList;

import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Darina Locovna on 2/7/17
 */

public class NoteListFragment extends Fragment {
  private static final String EXTRA_NOTE_ID =
      "com.locovna.notatka.noteintent.noteId";

  private RecyclerView mNoteRecyclerView;
  private NoteAdapter mAdapter;

  private int mLastAdapterClickPosition = -1;

  @Override
  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

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

  @Override
  public void onResume(){
    super.onResume();
    updateUI();
  }

  private void updateUI() {
    NotesList notesList = NotesList.get(getActivity());
    List<Note> notes = notesList.getNotesList();

    if (mAdapter == null){
      mAdapter = new NoteAdapter(notes);
      mNoteRecyclerView.setAdapter(mAdapter);
    } else {
      if (mLastAdapterClickPosition < 0) {
        mAdapter.notifyDataSetChanged();
      } else {
        mAdapter.setNotes(notes);
        mAdapter.notifyItemChanged(mLastAdapterClickPosition);
        mLastAdapterClickPosition = -1;
      }
    }
  }

  public class NoteHolder extends RecyclerView.ViewHolder
                          implements View.OnClickListener{
    private Note mNote;

    @BindView(R.id.list_item_note_title) TextView mNoteTitle;
    @BindView(R.id.list_item_note_body) TextView mNoteBody;

    public NoteHolder(View itemView){
      super(itemView);
      itemView.setOnClickListener(this);
      ButterKnife.bind(this, itemView);
    }

    public void bindNote(Note note){
      mNote = note;

      mNoteTitle.setText(mNote.getTitle());
      mNoteBody.setText(mNote.getBody());
    }

    @Override
    public void onClick(View v) {
      mLastAdapterClickPosition = getAdapterPosition();
      Intent intent = EditorActivity.newIntent(getActivity(),
          mNote.getId());
      startActivity(intent);
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

    public void setNotes(List<Note> notes){
      mNotes = notes;
    }
  }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
      super.onCreateOptionsMenu(menu, inflater);
      inflater.inflate(R.menu.menu_notelist, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      switch (item.getItemId()) {

        case R.id.action_new_note:
          Note note = new Note();
          NotesList.get(getActivity()).addNote(note);
          Intent intent = newIntent(getActivity(), note.getId());
          startActivity(intent);
          return true;

        case R.id.action_order_by_name:
        return true;

        case R.id.action_delete_all_notes:
          return true;
      }
      return super.onOptionsItemSelected(item);
    }

  public static Intent newIntent(Context packageContext, UUID noteId) {
    Intent intent = new Intent(packageContext, EditorActivity.class);
    intent.putExtra(EXTRA_NOTE_ID, noteId);
    return intent;
  }

}
