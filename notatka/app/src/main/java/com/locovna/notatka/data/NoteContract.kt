package com.locovna.notatka.data

import android.provider.BaseColumns

/**
 * Created by Darina Locovna on 1/22/17
 */

object NoteContract {

    class NoteEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "notes"

            val _ID = BaseColumns._ID
            val UUID = "uuid"
            val COLUMN_NOTE_TITLE = "title"
            val COLUMN_NOTE_TEXTBODY = "textbody"
        }
    }
}
