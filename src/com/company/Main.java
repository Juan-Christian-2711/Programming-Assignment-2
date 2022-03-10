package com.company;

import java.util.*;

import static com.company.Books.*;

public class Main {
    public static void main(String[] args) {
        //runs main menu
        Greeting();
        MainMenu();
    }
    public static void Greeting(){
        System.out.println(" _____       _     _________________  ___  ________   __\n" +
                "|  ___|     | |   |_   _| ___ \\ ___ \\/ _ \\ | ___ \\ \\ / /\n" +
                "| |__ ______| |     | | | |_/ / |_/ / /_\\ \\| |_/  ∧ V / \n" +
                "|  __|______| |     | | | ___ \\    /|  _  ||    /  \\ /  \n" +
                "| |___      | |_____| |_| |_/ / |\\ \\| | | || |\\ \\  | |  \n" +
                "\\____/      \\_____/\\___/\\____/\\_| \\_\\_| |_/\\_| \\_| \\_/ ");
    }
    public static void MainMenu() {
        //Prompts user to choose what to do
        Scanner Scanner = new Scanner(System.in);
        System.out.println(
                "\n"+"Menu: " +
                "\nadd a new book = 0" +
                "\nSee all books = 1" +
                "\nSearch Books = 2" +
                "\nRemove Books = 3" +
                "\nEdit Book = 4" + // needs to you set functions
                "\nSort Books by index = 5"+ // change this to page number
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
                removeBooks(askForID());
                System.out.println("Book Deleted Successfully");
                break;
            case "4":
                editBooks();
                break;
            case "5":
                sortBooks();
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