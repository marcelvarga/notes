package com.marcelvarga.notes;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Note {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "note_text")
    String noteText;

    @ColumnInfo(name = "last_edited")
    String lastEditedTime;

    @ColumnInfo(name = "created_time")
    String createdTime;

    public Note(String noteText){
        Date currentTime = Calendar.getInstance().getTime();
        this.uid = 0;
        this.noteText = noteText;
        this.createdTime = currentTime.toString();
        this.lastEditedTime = this.createdTime;
    }
    public String toString(){
        return noteText + " " + uid;
    }
}
