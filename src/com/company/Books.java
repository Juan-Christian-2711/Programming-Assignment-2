package com.company;

import java.io.FileWriter;

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

    public void writeToCSV(){
        FileWriter writer = new FileWriter("Books.csv");
        writer.append(BookID);
    }
}
