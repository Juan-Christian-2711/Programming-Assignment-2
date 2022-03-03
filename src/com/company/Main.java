package com.company;
import java.awt.print.Book;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;

import static com.company.Books.saveBook;

public class Main {
    public static void main(String[] args){

        //runs main menu
        MainMenu();
        readBooks();
    }
    public static void MainMenu(){
        //Prompts user to choose what to do
        Scanner Scanner = new Scanner(System.in);
        System.out.println("Menu: " +
                "\nadd a new book = 0" +
                "\nquit application = 2");
        String answer = Scanner.nextLine();
        if (answer.equals("0")){
            //runs code to add a new book
            InputBookDetails();
            MainMenu();
        }
        else if (answer.equals("2")){
            System.out.println("Exiting application...");
        }
        else{
            System.out.println("Syntax error");
            MainMenu();
        }

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
}

