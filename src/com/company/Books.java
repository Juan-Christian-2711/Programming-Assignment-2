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
        List<ArrayList<String>> listBooks = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < Books.size() ; i++){
            System.out.print(Books.get(i) + " ");
            if ((i+ 1) % 4 == 0){
                System.out.println(" ");
            }
        }
    }
    public static ArrayList searchBooks(){
        List<String> Books = loadBooks();
        Scanner Scanner = new Scanner(System.in);
        System.out.print("Input ID of Book: ");
        String answer = Scanner.nextLine();
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < Books.size() ; i++){
            temp.add(Books.get(i));
            if ((i+ 1) % 4 == 0){
                if (temp.get(0).equals(answer)){
                    return temp;
                }
                else {
                    temp.clear();
                }
            }
        }
        return null;
    }

    public static void InputBookDetails(){
        //This function, like many others in this application, should be reduced down as it has multiple functions. Each function should contain one function for the sake of sanity.
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
        BookDetails.addAll(loadBooks());
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
    public static void removeBooks(){
        Scanner Scanner = new Scanner(System.in);
        List<String> Books = loadBooks();
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<String> newList = new ArrayList<>();
        System.out.print("Input ID of Book: ");
        String answer = Scanner.nextLine();

        for (int i = 0; i < Books.size() ; i++){
            temp.add(Books.get(i));
            if ((i+ 1) % 4 == 0){
                if (!temp.get(0).equals(answer)){
                    newList.addAll(temp);
                }
                temp.clear();
            }
        }
        saveBook(newList);
        }
    public static void editBooks(){
        //In the criteria of this assignment, this
        ArrayList<String> Book = searchBooks();
        removeBooks();
        InputBookDetails();
    }
    public static void sortBooks(){
        //I want to create a list of lists, then sort that list of lists
        List<String> Books = loadBooks();
        ArrayList<String> temp = new ArrayList<>();
        List<ArrayList<String>> newList = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < Books.size() ; i++){
            temp.add(Books.get(i));
            if ((i+ 1) % 4 == 0){
                newList.add(temp);
            }
        }
    }
}
