package com.example.mytaskmate;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
//    calling data from repo class
    private NoteRepo noteRepo;
    private LiveData<List<Note>> listofnote;
    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepo = new NoteRepo(application);
        listofnote = noteRepo.getAllData();
    }

    public void insert(Note note){
         noteRepo.insertData(note);
    }

    public void  delete(Note note){
        noteRepo.deleteData(note);
    }

    public void update(Note note){
        noteRepo.updateData(note);
    }

    public LiveData<List<Note>> getAllNotes(){
        return listofnote;
    }
}
