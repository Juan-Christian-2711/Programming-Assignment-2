package com.company;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BooksTest {

    @org.junit.jupiter.api.Test
    void setBookID() {
        Books Book = new Books();
        Book.setBookID("1");
        String testString = Books.getBookID();
        assertEquals("1", testString);
        assertTrue(Books.stringIsNumber(testString));


        Book.setBookID("f");
        testString = Books.getBookID();
        assertEquals("0", testString);
        assertTrue(!Books.stringIsNumber(testString));
    }

    @org.junit.jupiter.api.Test
    void setTitle() {
        Books Book = new Books();
        Book.setTitle("The Watchmen");
        assertEquals("The Watchmen", Books.getTitle());
    }

    @org.junit.jupiter.api.Test
    void setPages() {
        Books Book = new Books();
        Book.setBookID("414");
        String testString = Books.getBookID();
        assertEquals("414", testString);
        assertTrue(Books.stringIsNumber(testString));


        Book.setBookID("f");
        testString = Books.getBookID();
        assertEquals("0", testString);
        assertTrue(!Books.stringIsNumber(testString));
    }

    @org.junit.jupiter.api.Test
    void setGenre() {
        Books Book = new Books();
        Book.setGenre("Graphic Novel");
        assertEquals("Graphic Novel", Books.getGenre());
    }

    @org.junit.jupiter.api.Test
    void setBook() {
        ArrayList Book = new ArrayList<String>();
        Book.add("1");
        Book.add("The Watchmen");
        Book.add("Graphic Novel");
        Book.add("414");
        Books.SetBook(Book);

        assertEquals("1", Books.getBookID());
        assertTrue(Books.stringIsNumber(Books.getBookID()));

        assertEquals("The Watchmen", Books.getTitle());

        assertEquals("Graphic Novel", Books.getGenre());

        assertEquals(414, Books.getPages());
    }

    @org.junit.jupiter.api.Test
    void saveBook() {

    }

    @org.junit.jupiter.api.Test
    void loadBooks() {
    }

    @org.junit.jupiter.api.Test
    void readBooks() {
    }

    @org.junit.jupiter.api.Test
    void searchBooks() {
    }

    @org.junit.jupiter.api.Test
    void inputBookDetails() {
    }

    @org.junit.jupiter.api.Test
    void removeBooks() {
    }

    @org.junit.jupiter.api.Test
    void editBooks() {
    }

    @org.junit.jupiter.api.Test
    void sortBooks() {
    }

    @org.junit.jupiter.api.Test
    void stringIsNumber() {
        assertTrue(Books.stringIsNumber("1"));
        assertTrue(Books.stringIsNumber("100"));
        assertTrue(Books.stringIsNumber("1000"));
        assertTrue(Books.stringIsNumber("1000000000"));
        //Method is limited to how many numbers can fit in an int
        assertTrue(Books.stringIsNumber("10000000000"));

        assertFalse(Books.stringIsNumber("f"));
        assertFalse(Books.stringIsNumber("Ten"));
        assertFalse(Books.stringIsNumber(""));
    }

    @org.junit.jupiter.api.Test
    void askForID() {
    }
}