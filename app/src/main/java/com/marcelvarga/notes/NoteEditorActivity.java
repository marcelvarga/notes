package com.marcelvarga.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NoteEditorActivity extends AppCompatActivity {

    int noteId;
    String noteText;
    EditText etNoteText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        // Show back button on actionbar
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etNoteText = findViewById(R.id.noteText);

        // Get data from note
        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId", -1);
        noteText = intent.getStringExtra("noteText");

        Toast toast = Toast.makeText(getApplicationContext(), String.valueOf(noteId), Toast.LENGTH_SHORT);
        toast.show();

        // Note exists in database
        if (noteId != -1){
            Log.i("myTag", noteText);
            etNoteText.setText(noteText);
        }
        //TODO note that isn't yet in the database
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void finish() {
        Intent result = new Intent();

        String updatedText = etNoteText.getText().toString();

        //Put new data in intent to send it to caller activity
        result.putExtra("noteText", updatedText);
        result.putExtra("noteId", noteId);
        setResult(RESULT_OK, result);

        Log.i("myTag", "data " + result.toString());
        super.finish();
    }
}