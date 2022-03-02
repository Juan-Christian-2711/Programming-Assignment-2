package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args){
        //runs main menu
       MainMenu();
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

