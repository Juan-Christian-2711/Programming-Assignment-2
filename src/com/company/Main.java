package com.company;
import java.util.*;
import static com.company.Books.*;

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

}

