package com.locovna.notatka.controller;

import android.support.v4.app.Fragment;

import com.locovna.notatka.SingleFragmentActivity;

/**
 * Created by Darina Locovna on 2/7/17
 */

public class NoteListActivity  extends SingleFragmentActivity{
  @Override
  protected Fragment createFragment(){
    return new NoteListFragment();
  }
}
