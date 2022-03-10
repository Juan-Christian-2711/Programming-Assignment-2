package com.company;

import java.awt.image.BandedSampleModel;
import java.awt.print.Book;
import java.io.*;
import java.util.*;

public class Books implements Serializable {
    private static String BookID;
    private static String Title;
    private static int pages;
    private static String Genre;

    public static String getBookID() {
        return BookID;
    }

    public static String getTitle() {
        return Title;
    }

    public static int getPages() {
        return pages;
    }

    public static String getGenre() {
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

    public static void SetBook(ArrayList <String> BookDetails){
        Books Book = new Books();
        Book.setBookID(BookDetails.get(0));
        Book.setTitle(BookDetails.get(1));
        Book.setGenre(BookDetails.get(2));
        Book.setPages(Integer.parseInt(BookDetails.get(3)));
    }

    public static ArrayList GetBook(){
        ArrayList<String> Book = new ArrayList<>();
        Book.add(getBookID());
        Book.add(getTitle());
        Book.add(getGenre());
        Book.add(String.valueOf(getPages()));
        return Book;
    }
// create a get book function
    public static void saveBook(List<String> books) {
        try {
            FileOutputStream FOS = new FileOutputStream("save.dat");
            ObjectOutputStream OOS = new ObjectOutputStream(FOS);
            OOS.writeObject(books);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Unable to create object output stream: " + e.getMessage());
        }
    }

    public static List<String> loadBooks() {
        try {
            FileInputStream FIS = new FileInputStream("save.dat");
            ObjectInputStream OIS = new ObjectInputStream(FIS);
            List<String> Books = (List<String>) OIS.readObject();
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

    public static void readBooks() {
        List<String> Books = loadBooks();
        List<ArrayList<String>> listBooks = new ArrayList<ArrayList<String>>();
        if(Books.size() == 0){
            System.out.println("No books have been added.");
        }
        else {
            for (int i = 0; i < Books.size(); i++) {
                System.out.print(Books.get(i) + " ");
                if ((i + 1) % 4 == 0) {
                    System.out.println(" ");
                }
            }
        }
    }

    public static ArrayList<String> searchBooks() {
        List<String> Books = loadBooks();
        String answer = askForID();
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < Books.size(); i++) {
            temp.add(Books.get(i));
            if ((i + 1) % 4 == 0) {
                if (temp.get(0).equals(answer)) {
                    return temp;
                } else {
                    temp.clear();
                }
            }
        }
        return null;
    }

    public static void InputBookDetails() {
        Scanner Scanner = new Scanner(System.in);
        String[] enterPhrase = {"Enter Book ID: ", "Enter Title: ", "Enter Genre: ", "Enter Pages: "};
        List<String> BookDetails = new ArrayList<>();
        boolean check = true;
        for (int i = 0; i < enterPhrase.length; i++) {
            if(i == 3){
                System.out.println(enterPhrase[i]);
                String Item = Scanner.nextLine();
                while(!stringIsNumber(Item)){
                    System.out.println("Pages must be a number\n" +enterPhrase[i]);
                    Item = Scanner.nextLine();
                }
                BookDetails.add(Item);
            }
            else{
                System.out.println(enterPhrase[i]);
                String Item = Scanner.nextLine();
                BookDetails.add(Item);
            }

        }
        BookDetails.addAll(loadBooks());
        saveBook(BookDetails);
        boolean notcomplete = true;
        while (notcomplete) {
            System.out.println("Would you like to add another book? (y/n)");
            String answer = Scanner.nextLine();
            if (answer.equals("y")) {
                notcomplete = false;
                InputBookDetails();
            } else if (answer.equals("n")) {
                return;
            } else {
                System.out.println("Incorrect input:");
                System.out.println(answer);
            }
        }
    }

    public static void removeBooks(String answer) {
        List<String> Books = loadBooks();
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<String> newList = new ArrayList<>();//this code is an exact repeat of code from search books
        for (int i = 0; i < Books.size(); i++) {
            temp.add(Books.get(i));
            if ((i + 1) % 4 == 0) {
                if (!temp.get(0).equals(answer)) {
                    newList.addAll(temp);
                }
                temp.clear();
            }
        }
        saveBook(newList);
    }

    public static void editBooks() {
        Books Books = new Books();
        //In the criteria of this assignment, this
        //Use the set functions in this class to change the properties of the object as an object instead of as an array
        ArrayList<String> Book = searchBooks();

        removeBooks(Book.get(0));
        SetBook(Book);
        //case switch to choose what parameter to set
        boolean userWantsToEdit = true;
        while(userWantsToEdit){
            Scanner Scanner = new Scanner(System.in);

            System.out.println("Select what would you like to edit?\n"+
                    "BookID = 0\n"+
                    "Title = 1\n"+
                    "Genre = 2\n"+
                    "Pages = 3\n" +
                    "Back to main menu = x");
            String answer = Scanner.nextLine();
            String Set;
            switch (answer){
                case("0"):
                    System.out.println();
                    Set = Scanner.nextLine();
                    Books.setBookID(Set);
                    break;
                case("1"):
                    System.out.println();
                    Set = Scanner.nextLine();
                    Books.setTitle(Set);
                    break;
                case("2"):
                    System.out.println();
                    Set = Scanner.nextLine();
                    Books.setGenre(Set);
                    break;
                case("3"):
                    System.out.println();
                    Set = Scanner.nextLine();
                    Books.setPages(Integer.parseInt(Set));
                    break;
                case("x"):
                    return;
                default:
                    System.out.println("Syntax error");
                    break;
            }

        }

        ArrayList bookSave = GetBook();
        bookSave.addAll(loadBooks());
        sortBooks();
    }

    public static void sortBooks() {
        //I want to create a list of lists, then sort that list of lists
        ArrayList<String> Books = (ArrayList<String>) loadBooks();
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<ArrayList<String>> newList = new ArrayList<>();
        for (int i = 0; i < Books.size(); i++) {
            temp.add(Books.get(i));
            if ((i + 1) % 4 == 0) {
                int end = temp.size();
                int start = temp.size() -4;
                ArrayList<String> addList = new ArrayList<String>(temp.subList(start, end));
                newList.add(addList);
            }
        }
        System.out.println(newList);
        ArrayList<String> sorted = new ArrayList<>();
        for(int i = 0; i < newList.size(); i++){
            for(int x = 0; x < newList.size(); x++){
                if(i + 1 == Integer.parseInt(newList.get(x).get(0))) {
                    for(int z = 0; z < newList.get(x).size(); z++ ){
                        sorted.add(newList.get(x).get(z));
                    }
                }
            }
        }
        System.out.println(sorted);
        saveBook(sorted);
    }

    public static boolean stringIsNumber(String str){
        try{
            Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException nfe){
            return false;
        }
    }
    public static String askForID(){
        Scanner Scanner = new Scanner(System.in);
        System.out.print("Input ID of Book: ");
        String answer = Scanner.nextLine();
        while(!stringIsNumber(answer)){
            System.out.print("Book ID must be a number\nInput ID of Book: ");
            answer = Scanner.nextLine();//this code is an exact repeat of code from search books
        }
        return answer;
    }
}