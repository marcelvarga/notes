package com.marcelvarga.notes;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.io.Serializable;
import java.util.List;

@Dao
public interface NoteDao{

    @Query("SELECT * FROM note ORDER BY last_edited")
    public List<Note> getAll();

    @Query("SELECT * FROM note")
    public List<Note> getAllUnsorted();

    @Query("SELECT * FROM note WHERE uid = :id")
    public Note findById(int id);

    @Insert
    public void insert(Note... notes);

    @Delete
    public void delete(Note note);

    @Query("DELETE FROM note WHERE uid= :id")
    public void deleteById(int id);

    @Update
    public void update(Note note);
}
