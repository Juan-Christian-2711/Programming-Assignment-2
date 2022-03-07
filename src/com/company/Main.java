package com.company;
import java.util.*;
import static com.company.Books.*;

public class Main {
    public static void main(String[] args) {
        //runs main menu
        MainMenu();
    }
    public static void MainMenu() {
        //Prompts user to choose what to do
        Scanner Scanner = new Scanner(System.in);
        System.out.println("Menu: " +
                "\nadd a new book = 0" +
                "\nSee all books = 1" +
                "\nSearch Books = 2"+
                "\nRemove Books = 3" +
                "\nEdit Book = 4"+
                "\nquit application = x");
        String answer = Scanner.nextLine();
        switch (answer) {
            case "0":
                InputBookDetails();
                break;
            case "1":
                readBooks();
                break;
            case "2":
                System.out.println(searchBooks());
                break;
            case "3":
                removeBooks();
                break;
            case "4":
                editBooks();
                break;
            case "x":
                System.out.println("Exiting application...");
                System.exit(0);
            default:
                System.out.println("Syntax error");
                break;
        }
        System.out.println("\n");
        MainMenu();
    }
}