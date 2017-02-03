package com.locovna.notatka.model;

/**
 * Created by Darina Locovna on 1/17/17
 */

public class Note {
  private String mTitle;
  private String mBody;

  public Note(String title, String body) {
    mTitle = title;
    mBody = body;
  }

  public String getTitle() {
    return mTitle;
  }

  public void setTitle(String title) {
    mTitle = title;
  }

  public String getBody() {
    return mBody;
  }

  public void setBody(String body) {
    mBody = body;
  }
}
