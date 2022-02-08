package com.company;

public class Books {
    private int BookID;
    private String Title;
    private int pages;
    private String Genre;
    private boolean NonFiction;
    public int getBookID() {
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

    public boolean isNonFiction() {
        return NonFiction;
    }

    public void setBookID(int bookID) {
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

    public void setNonFiction(boolean non) {
        NonFiction = non;
    }
}
