package com.locovna.notatka.model

import java.util.*

/**
 * A single note object
 *
 * @property id the name of this note.
 * @property title the title of this note.
 * @property body the text body of this note.
 * @constructor Creates an empty note with random id.
 */

class Note() {
    var id: UUID
    var title: String? = ""
    var body: String? = ""

    init {
        this.id = UUID.randomUUID()
    }

   constructor(noteId : UUID) : this() {
    this.id = noteId
   }

}
