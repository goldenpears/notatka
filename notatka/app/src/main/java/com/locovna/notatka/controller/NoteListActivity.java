package com.locovna.notatka.controller;

import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.locovna.notatka.R;
import com.locovna.notatka.SingleFragmentActivity;

/**
 * Created by Darina Locovna on 2/7/17
 */

public class NoteListActivity extends SingleFragmentActivity {
  @Override
  protected Fragment createFragment() {
    return new NoteListFragment();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_notelist, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_new_note:
//        Intent intent = new Intent(NoteListActivity.this, EditorActivity.class);
//        startActivity(intent);
        return true;
      case R.id.action_order_by_name:
      return true;
      case R.id.action_delete_all_notes:
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
