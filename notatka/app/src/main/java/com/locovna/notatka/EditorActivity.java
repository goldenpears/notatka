package com.locovna.notatka;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.locovna.notatka.controller.NoteFragment;

import java.util.UUID;

public class EditorActivity extends SingleFragmentActivity {
  public static final String TAG = EditorActivity.class.getSimpleName();

  private static final String EXTRA_NOTE_ID =
      "com.locovna.notatka.noteintent.noteId";

  public static Intent newIntent(Context packageContext, UUID noteId) {
    Intent intent = new Intent(packageContext, EditorActivity.class);
    intent.putExtra(EXTRA_NOTE_ID, noteId);
    return intent;
  }

  @Override
  protected Fragment createFragment() {
    UUID noteId = (UUID) getIntent().getSerializableExtra(EXTRA_NOTE_ID);
    return NoteFragment.newInstance(noteId);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_editor, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_delete_current_note_:
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
