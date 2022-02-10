package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner Scanner = new Scanner(System.in);
        System.out.println("Would you like to read or write?(1/0)");
        String answer = Scanner.nextLine();
        if(answer.equals("1")){
            Books.readJSON();
        }
        else {
            InputBookDetails();
        }
        Books.readJSON();
    }
    public static void InputBookDetails(){
        Books Book = new Books();
        Scanner Scanner = new Scanner(System.in);
        String[] enterPhrase = {"Enter Book ID: ", "Enter Title: ", "Enter Genre: ","Enter Pages: "};
        List<String> BookDetails=new ArrayList<String>();
        for (String s : enterPhrase) {
            System.out.println(s);
            String Item = Scanner.nextLine();
            BookDetails.add(Item);
        }
        Book.setBookID(BookDetails.get(0));
        Book.setTitle(BookDetails.get(1));
        Book.setGenre(BookDetails.get(2));
        Book.setPages(Integer.parseInt(BookDetails.get(3)));
        Book.writeToJSON(Book.getBookID(), Book.getTitle(), Book.getPages(), Book.getGenre());
        boolean notcomplete = true;
        while (notcomplete){
            System.out.println("Would you like to add another book? (y/n)");
            String answer = Scanner.nextLine();
            if(answer.equals("y")){
                notcomplete = false;
                InputBookDetails();
            }
            else if (answer.equals("n")){
                notcomplete = false;
                return;
            }
            else{
                System.out.println("Incorrect input:");
                System.out.println(answer);
            }
        }

    }
}
