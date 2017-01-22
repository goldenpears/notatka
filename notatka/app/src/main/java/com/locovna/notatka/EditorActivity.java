package com.locovna.notatka;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class EditorActivity extends AppCompatActivity {

  private EditText mTitleEditText;

  private EditText mTextbodyEditText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_editor);

    mTitleEditText = (EditText) findViewById(R.id.edit_note_title);
    mTextbodyEditText = (EditText) findViewById(R.id.edit_note_textbody);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_editor, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_save:
        // Do nothing for now
        return true;
      case R.id.action_delete:
        // Do nothing for now
        return true;
      case android.R.id.home:
        // Navigate back to parent activity (CatalogActivity)
        NavUtils.navigateUpFromSameTask(this);
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
