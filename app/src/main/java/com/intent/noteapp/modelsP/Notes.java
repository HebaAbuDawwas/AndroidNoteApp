package com.intent.noteapp.modelsP;

public class Notes {
    String note,notetitle;
    int id;

    public Notes(int id ,String note, String notetitle) {
        this.note = note;

        this.id=id;
        this.notetitle=notetitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public String getNotetitle() {
        return notetitle;
    }

    public void setNotetitle(String notetitle) {
        this.notetitle = notetitle;
    }



}
