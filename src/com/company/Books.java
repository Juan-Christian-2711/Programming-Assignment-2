package com.company;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
        JSONObject Book = new JSONObject();
        Book.put("BookID", BookID);
        Book.put("Title", Title);
        Book.put("Pages", Pages);
        Book.put("Genre", Genre);
        JSONArray BookList = readJSON();
        BookList.add(Book);
        System.out.println(Book.toJSONString());
        try (FileWriter file = new FileWriter("Books.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(BookList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static JSONArray readJSON(){
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("Books.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray BookList = (JSONArray) obj;
            System.out.println(BookList);
            return BookList;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
