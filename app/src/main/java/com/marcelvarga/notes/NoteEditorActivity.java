package com.marcelvarga.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class NoteEditorActivity extends AppCompatActivity {

    int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        EditText etNoteText = findViewById(R.id.noteText);

        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId", -1);

    }
}