package com.company;
public class Main {
    public static void main(String[] args){
        Books Book = new Books();
        Book.setBookID("1");
        Book.setTitle("Watchmen");
        Book.setGenre("Graphic Novel");
        Book.setPages(414);
        Book.writeToCSV();
        System.out.println("Book ID: " +Book.getBookID() + "\nBook Genre: " + Book.getGenre() + "\nPages: " + Book.getPages() + "\nTitle: " + Book.getTitle());
    }
}
