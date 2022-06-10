package com.marcelvarga.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = findViewById(R.id.gridView);

        // Initiate Database connection and get all notes text
        AppDatabase database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "notes").allowMainThreadQueries().build();
        NoteDao noteDao = database.noteDao();

        // Testing purposes, add text to database
        Note note0 = new Note("Test");
        Note note1 = new Note("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas augue mauris, pharetra non neque mollis, egestas placerat libero. Quisque at mauris iaculis, cursus sem a, volutpat ligula. Fusce libero neque, volutpat sit amet venenatis in, maximus et urna. Etiam aliquam, ante et congue pulvinar, dolor odio commodo diam, sit amet pellentesque felis purus commodo massa. Nam risus risus, fermentum vel massa ac, viverra vestibulum orci. Cras convallis leo sed ligula venenatis cursus. Nam vitae semper metus. Proin suscipit condimentum tincidunt. Ut malesuada malesuada ante. Suspendisse dignissim ex eu eleifend euismod. Curabitur nec lobortis purus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Fusce blandit congue lectus, eu pulvinar risus suscipit eu. Nulla eu rhoncus sem. Vivamus facilisis, purus ac porttitor imperdiet, libero erat molestie massa, blandit blandit nisl augue nec lectus.");

        noteDao.insert(note0, note1, note1, note0, note0, note1, note1, note1, note0, note1, note1, note0, note1, note1, note0, note1, note1);

        //String element = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas augue mauris, pharetra non neque mollis, egestas placerat libero. Quisque at mauris iaculis, cursus sem a, volutpat ligula. Fusce libero neque, volutpat sit amet venenatis in, maximus et urna. Etiam aliquam, ante et congue pulvinar, dolor odio commodo diam, sit amet pellentesque felis purus commodo massa. Nam risus risus, fermentum vel massa ac, viverra vestibulum orci. Cras convallis leo sed ligula venenatis cursus. Nam vitae semper metus. Proin suscipit condimentum tincidunt. Ut malesuada malesuada ante. Suspendisse dignissim ex eu eleifend euismod. Curabitur nec lobortis purus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Fusce blandit congue lectus, eu pulvinar risus suscipit eu. Nulla eu rhoncus sem. Vivamus facilisis, purus ac porttitor imperdiet, libero erat molestie massa, blandit blandit nisl augue nec lectus.";
        //List<String> list = new ArrayList<>(Arrays.asList(element, element, element, element, element, element, element, element, element, element));

        // Add notes to gridView
        List<Note> notesList = noteDao.getAll();
        ListAdapter listAdapter = new NotesListAdapter(this, R.layout.linear_layout, notesList);
        //ListAdapter listAdapter1 = new SimpleCursorAdapter(this, R.layout.linear_layout, null, )
        gridView.setAdapter(listAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast toast = Toast.makeText(getApplicationContext(), String.valueOf(position) + " " +String.valueOf(id), Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        noteDao.delete(notesList.get(1));
    }
}