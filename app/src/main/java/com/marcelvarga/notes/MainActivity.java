package com.marcelvarga.notes;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public NoteDao noteDao;
    List<Note> notesList;
    GridView gridView;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);

        // Initiate Database connection and get all notes text
        AppDatabase database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "notes").allowMainThreadQueries().build();
        noteDao = database.noteDao();

        // Testing purposes, add text to database
        Note note0 = new Note("Test");
        //Note note1 = new Note("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas augue mauris, pharetra non neque mollis, egestas placerat libero. Quisque at mauris iaculis, cursus sem a, volutpat ligula. Fusce libero neque, volutpat sit amet venenatis in, maximus et urna. Etiam aliquam, ante et congue pulvinar, dolor odio commodo diam, sit amet pellentesque felis purus commodo massa. Nam risus risus, fermentum vel massa ac, viverra vestibulum orci. Cras convallis leo sed ligula venenatis cursus. Nam vitae semper metus. Proin suscipit condimentum tincidunt. Ut malesuada malesuada ante. Suspendisse dignissim ex eu eleifend euismod. Curabitur nec lobortis purus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Fusce blandit congue lectus, eu pulvinar risus suscipit eu. Nulla eu rhoncus sem. Vivamus facilisis, purus ac porttitor imperdiet, libero erat molestie massa, blandit blandit nisl augue nec lectus.");
        Note note1 = note0;
        noteDao.insert(note0, note1, note1, note0, note0, note1, note1, note1, note0, note1, note1, note0, note1, note1, note0, note1, note1);

        // Add notes to gridView
        notesList = noteDao.getAll();
        listAdapter = new NotesListAdapter(this, R.layout.linear_layout, notesList);

        //ListAdapter listAdapter1 = new SimpleCursorAdapter(this, R.layout.linear_layout, null, )
        gridView.setAdapter(listAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast toast = Toast.makeText(getApplicationContext(), String.valueOf(position) + " " +String.valueOf(id), Toast.LENGTH_SHORT);
                toast.show();

                Intent intent = new Intent(getApplicationContext(), NoteEditorActivity.class);


                intent.putExtra("noteId", (int)id);
                intent.putExtra("noteText", noteDao.findById((int)id).noteText);
                getNoteText.launch(intent);

            }
        });
        //noteDao.deleteById(9);

    }

    ActivityResultLauncher<Intent> getNoteText = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == RESULT_OK){

                        // Get data from called activity
                        Intent resultIntent = result.getData();
                        assert resultIntent != null;
                        String updatedText = resultIntent.getStringExtra("noteText");
                        int noteId = resultIntent.getIntExtra("noteId", -1);

                        Log.i("myTag", updatedText);

                        // If note already existed, update its text
                        if (noteId != -1){
                            Note note = noteDao.findById(noteId);
                            if(!updatedText.equals(note.noteText)){
                                note.noteText = updatedText;
                                noteDao.update(note);
                            }
                        }

                        //TODO update view to show changes in GridView
                        notesList = noteDao.getAll();

                        Log.i("myTag", notesList.toString());
                    }
                }
            }
    );

    @Override
    protected void onResume() {
        //noteDao.delete(notesList.remove(0));
        notesList = noteDao.getAll();
        super.onResume();
    }
}