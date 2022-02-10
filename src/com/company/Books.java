package com.company;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.awt.print.Book;
import java.io.*;

public class Books {
    private String BookID;
    private String Title;
    private int pages;
    private String Genre;

    public String getBookID() {
        return BookID;
    }

    public String getTitle() {
        return Title;
    }

    public int getPages() {
        return pages;
    }

    public String getGenre() {
        return Genre;
    }

    public void setBookID(String bookID) {
        this.BookID = bookID;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public void writeToJSON(String BookID, String Title, int Pages, String Genre){
        JSONObject Books = new JSONObject();
        Books.put("BookID", BookID);
        Books.put("Title", Title);
        Books.put("Pages", Pages);
        Books.put("Genre", Genre);
        System.out.println(Books.toJSONString());
        JSONArray Booklist = new JSONArray();
        Booklist.add(Books);
        try (FileWriter file = new FileWriter("Books.json", true)) {
            //We can write any JSONArray or JSONObject instance to the file
            file.append(Booklist.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readJSON(){

    }
}
