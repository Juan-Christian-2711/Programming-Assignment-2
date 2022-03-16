package com.company;

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

    public static ArrayList<String> GetBook(){
        ArrayList<String> Book = new ArrayList<>();
        Book.add(getBookID());
        Book.add(getTitle());
        Book.add(getGenre());
        Book.add(String.valueOf(getPages()));
        return Book;
    }
    public static void saveBook(List<String> books) {
        //Saves the book data to the file save.dat in the list "books".
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
        //Loads book data from the save.dat file.
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

    public static String readBooks() {
        //parses the loadBook data into a more readable format and returns it as a string.
        List<String> Books = loadBooks();
        String Output = "";
        if(Books.size() == 0){
            Output = "No books have been added.";
        }
        else {
            for (int i = 0; i < Books.size(); i++) {
                Output += Books.get(i) + " ";
                if ((i + 1) % 4 == 0) {
                    Output += "\n";
                }
            }
        }
        return Output;
    }

    public static ArrayList<String> searchBooks(String answer) {
        List<String> Books = loadBooks();
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
        String[] enterPhrase = {"Enter Title: ", "Enter Genre: ", "Enter Pages: "};
        List<String> BookDetails = new ArrayList<>();
        if(loadBooks().size() == 0){
            BookDetails.add("1");
        }
        else{
            BookDetails.add(String.valueOf(Integer.parseInt(loadBooks().get(loadBooks().size()-4)) + 1));
        }
        System.out.println("ID: " + BookDetails.get(0));
        boolean check = true;
        for (int i = 0; i < enterPhrase.length; i++) {
            if(i == 2){
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
        sortBooks();
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

    public static void removeBooks(String ID) {
        List<String> Books = loadBooks();
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<String> newList = new ArrayList<>();//this code is an exact repeat of code from search books
        for (int i = 0; i < Books.size(); i++) {
            temp.add(Books.get(i));
            if ((i + 1) % 4 == 0) {
                if (!temp.get(0).equals(ID)) {
                    newList.addAll(temp);
                }
                temp.clear();
            }
        }
        saveBook(newList);
    }
    public static void SaveEdit(){
        System.out.println("Updated Book: " + GetBook());
        System.out.println(loadBooks());
        ArrayList bookSave = new ArrayList<>();
        bookSave.addAll(GetBook());
        bookSave.addAll(loadBooks());
        saveBook(bookSave);
    }
    public static void editBooks(ArrayList<String> Book) {
        Books Books = new Books();
        //In the criteria of this assignment, this
        //Use the set functions in this class to change the properties of the object as an object instead of as an array
        removeBooks(Book.get(0));
        SetBook(Book);
        System.out.println(GetBook());
        //case switch to choose what parameter to set
        boolean userWantsToEdit = true;
        while(userWantsToEdit){
            Scanner Scanner = new Scanner(System.in);

            System.out.println("Select what would you like to edit?\n"+
                    "Title = 1\n"+
                    "Genre = 2\n"+
                    "Pages = 3\n" +
                    "Back to main menu = x");
            String answer = Scanner.nextLine();
            String Set;
            String request = "Input Change: ";
            switch (answer){
                case("1"):
                    System.out.println(request);
                    Set = Scanner.nextLine();
                    Books.setTitle(Set);
                    break;
                case("2"):
                    System.out.println(request);
                    Set = Scanner.nextLine();
                    Books.setGenre(Set);
                    break;
                case("3"):
                    System.out.println(request);
                    Set = Scanner.nextLine();
                    Books.setPages(Integer.parseInt(Set));
                    break;
                case("x"):
                    SaveEdit();
                    return;
                default:
                    System.out.println("Syntax error");
                    break;
            }

        }
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
        //Checks to see if a string can be converted to a number before attempting to, in order to avoid errors not being caught.
        try{
            Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException nfe){
            return false;
        }
    }
    public static String askForID(){
        //prompts user for an ID number, checks to see if it is a number and returns the ID.
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