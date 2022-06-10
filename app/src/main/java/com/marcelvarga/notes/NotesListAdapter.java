package com.marcelvarga.notes;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

public class NotesListAdapter extends ArrayAdapter<Note>{

    public NotesListAdapter(@NonNull Context context, int resource, @NonNull List<Note> objects) {
        super(context, resource, objects);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).uid;
    }
}