package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Books implements Serializable {
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
    public static void saveBook(List<String> books){
        try{
            FileOutputStream FOS = new FileOutputStream( "save.dat");
            ObjectOutputStream OOS = new ObjectOutputStream(FOS);
            OOS.writeObject(books);
        }
        catch(FileNotFoundException e){
            System.out.println("Unable to save file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Unable to create object output stream: " + e.getMessage());
        }

    }
    public static List<String> loadBooks(){
        try {
            FileInputStream FIS = new FileInputStream("save.dat");
            ObjectInputStream OIS = new ObjectInputStream(FIS);
            List<String> Books = (List<String>)OIS.readObject();
            return Books;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error converting data to object" + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.print("Cant find class representing object");
        }

        return new ArrayList<String>();
    }
    public static void readBooks(){
        List<String> Books = loadBooks();
        System.out.println(Books);
    }

    public static void InputBookDetails(){
        Books Book = new Books();
        Scanner Scanner = new Scanner(System.in);
        String[] enterPhrase = {"Enter Book ID: ", "Enter Title: ", "Enter Genre: ","Enter Pages: "};
        List<String> BookDetails=new ArrayList<>();
        for (String s : enterPhrase) {
            System.out.println(s);
            String Item = Scanner.nextLine();
            BookDetails.add(Item);
        }
        Book.setBookID(BookDetails.get(0));
        Book.setTitle(BookDetails.get(1));
        Book.setGenre(BookDetails.get(2));
        Book.setPages(Integer.parseInt(BookDetails.get(3)));
        saveBook(BookDetails);
        boolean notcomplete = true;
        while (notcomplete){
            System.out.println("Would you like to add another book? (y/n)");
            String answer = Scanner.nextLine();
            if(answer.equals("y")){
                notcomplete = false;
                InputBookDetails();
            }
            else if (answer.equals("n")){
                return;
            }
            else{
                System.out.println("Incorrect input:");
                System.out.println(answer);
            }
        }
    }
}
