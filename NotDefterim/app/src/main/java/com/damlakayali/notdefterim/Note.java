package com.damlakayali.notdefterim;

import java.io.Serializable;

/**
 * Created by damla on 2.07.2017.
 */

public class Note implements Serializable{
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;
    String header;
    String content;
    int priority;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public Note() {
    }

    public void setContent(String content) {

        this.content = content;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Note(String header, String content, int priority) {

        this.header = header;
        this.content = content;
        this.priority = priority;
    }
}
