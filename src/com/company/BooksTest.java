package com.company;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BooksTest {

    public static ArrayList DummyData(){
        ArrayList Book = new ArrayList<String>();
        Book.add("1");
        Book.add("The Watchmen");
        Book.add("Graphic Novel");
        Book.add("414");
        Book.add("2");
        Book.add("Dante's Inferno");
        Book.add("Epic Poetry");
        Book.add("448");
        Books.saveBook(Book);
        return Book;
    }

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
        assertFalse(Books.stringIsNumber(testString));
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
        ArrayList Book = DummyData();
        List SavedBook = Books.loadBooks();
        for (int i = 0; i < SavedBook.size(); i++) {
            assertEquals(SavedBook.get(i), Book.get(i));
        }
        assertTrue(Books.stringIsNumber((String) SavedBook.get(3)));

    }

    @org.junit.jupiter.api.Test
    void readBooks() {
        ArrayList Book = new ArrayList<String>();
        Book.add("1");
        Book.add("The Watchmen");
        Book.add("Graphic Novel");
        Book.add("414");
        Books.saveBook(Book);
        assertEquals("1 The Watchmen Graphic Novel 414 \n", Books.readBooks());
        DummyData();
        assertEquals("1 The Watchmen Graphic Novel 414 \n2 Dante's Inferno Epic Poetry 448 \n", Books.readBooks());
    }

    @org.junit.jupiter.api.Test
    void searchBooks() {
        ArrayList Book = new ArrayList<String>();
        Book = DummyData();
        assertEquals(Book.subList(0, 4), Books.searchBooks("1"));
        assertEquals(Book.subList(4, 8), Books.searchBooks("2"));
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